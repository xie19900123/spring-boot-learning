## 前言
>最近收到公众号留言说，单纯的`Mybatis`的集成和使用。前面在第九章：[Mybatis-plus的集成和使用](http://blog.lqdev.cn/2018/07/21/springboot/chapter-nine/ "Mybatis-plus的集成和使用")介绍了基于`mybatis-plus`的集成和使用。后者也只是对`mybatis`进行了功能增强，原本的用法都是没有变化的。那今天就来简单介绍了如何`springboot`中如何集成和使用`Mybatis`吧。

*   [SpringBoot的集成和使用](#SpringBoot的集成和使用)
    *   [通用配置](#通用配置)
    *   [注解方式](#注解方式)
    *   [xml方式](#xml方式)
    *   [枚举类型处理器配置](#枚举类型处理器配置)
*   [参考资料](#参考资料)
*   [总结](#总结)
*   [最后](#最后)
*   [老生常谈](#老生常谈)

## SpringBoot的集成和使用
>`MyBatis`是一款优秀的持久层框架，它支持定制化SQL、存储过程以及高级映射。`MyBatis` 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。**`MyBatis`可以使用简单的`XML或`注解来配置和映射原生信息，将接口和Java的POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记**录。

这里介绍基于`xml`和`注解`两种方式进行配置。同时使用`mybatis-spring-boot-starter`进行集成。


>这里选用的**mybatis-spring-boot-starter**版本为：`1.3.2`。
对应**Mybatis**版本为：`3.4.6`

### 通用配置
**两种方式都引入一下的`pom`配置：**
```xml
<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
```

**这里以`user`表为例子，数据库为mysql**
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) DEFAULT NULL COMMENT '唯一标示',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `status` char(1) DEFAULT '1' COMMENT '状态 1启用 0 停用',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````

**实体类`User`为：**

```java
/**
 * <p>
 * 
 * </p>
 *
 * @author oKong
 * @since 2018-12-02
 */
@Data
@Accessors(chain = true)
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1779270373648636358L;
	/**
     * 唯一标示
     */
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    
    /**
     * 状态1 启用 0 停用
     */
    private StatusEnum status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
```

**状态枚举类`StatusEnum`：**

```java
public enum StatusEnum {
	
	DISABLE,
	ENABLE;

}
```

**配置文件：application.properties**
```

# 实体别名
mybatis.type-aliases-package=cn.lqdev.learning.springboot.chapter35.biz.entity

# 数据库配置
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/learning?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=

spring.profiles.active=anno
```

**启动类**

```java
/**
 * mybaits集成
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class MybatisApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MybatisApplication.class, args);
		log.info("spring-boot-mybatis-chapter35启动!");
	}
}
```

### 注解方式
0.创建注解版的mapper：

`UserMapper.java`

```java
/**
 * 注解配置
 * @author okong
 *
 */
public interface UserMapper {
    //配置返回的字段类型，这里配置了创建日期和修改日期自动
	@Select("select * from user where id = #{id}")
	@Results({
		@Result(column = "gmt_create",property = "gmtCreate",jdbcType=JdbcType.DATE),
		@Result(column = "gmt_modified",property = "gmtModified",jdbcType=JdbcType.DATE)
	})
	User queryOne(Long id);
	
	// 枚举类 默认是使用 EnumTypeHandler 处理类，即使用枚举name作为值
	//status 为枚举类 也可以直接指定了 typeHandler类 作为处理类 ，如：#{status,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}
	//还可以在sqlFactory 直接使用  TypeHandlerRegistry  进行注册 详看；MybatisConfig 类
	//最简单：自定义 ConfigurationCustomizer 了进行设置 详看；MybatisConfig 类
	@Insert("insert into user(code,name,status) values(#{code},#{name}, #{status})")
	//以下配置会对user对象进行id赋值
    @Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)
	int insert(User user);
	
	@Update("update user set code=#{code}, name = #{name}, status = #{status} where id=#{id}")
	void update(User user);
	
	@Delete("delete from user where id=#{id}")
	void delete(Long id);
	
	@Select("select * from user where code = #{code}")
	@Results({
		@Result(column = "gmt_create",property = "gmtCreate",jdbcType=JdbcType.DATE),
		@Result(column = "gmt_modified",property = "gmtModified",jdbcType=JdbcType.DATE)
	})	
	List<User> queryByParams(@Param("code")String code);
}
```
简单对以上相关注解进行说明下：

*   @Select 是查询类的注解，所有的查询均使用这个
*   @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
*   @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
*   @Update 负责修改，也可以直接传入对象
*   @delete 负责删除
*   @Options 映射语句的属性，如新增时需要返回自增的ID时:`@Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)`

具体的可以去官网查阅：[http://www.mybatis.org/mybatis-3/zh/java-api.html](http://www.mybatis.org/mybatis-3/zh/java-api.html "http://www.mybatis.org/mybatis-3/zh/java-api.html")

![映射器注解](http://qiniu.xds123.cn/18-12-2/10195302.jpg)

2.指定mapper扫描包路径，使用注解`@MapperScan`
```java
/**
 * mybaits配置
 * @author oKong
 *
 */
@Configuration
@MapperScan("cn.lqdev.learning.springboot.chapter35.biz.mapper")//mapper地址
public class MybatisConfig {
     
}
```
注意：若使用`Druid`进行数据连接池管理，也可以在此类中进行`DataSource`的相关配置。

3.编写测试类进行测试。
```java
/**
 * 测试类
 * @author oKong
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("anno")
@Slf4j
public class UserMapperTest {
	
	@Autowired
	UserMapper userMpper;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setCode("002");
		user.setName("name002");
		user.setStatus(StatusEnum.ENABLE);
		
		//新增
		userMpper.insert(user);
	}
	
	@Test
	public void testQueryOne() {
		User user = userMpper.queryOne(1L);
		log.info("id为1的查询结果为：{}", user);
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setCode("002");
		user.setName("testUpdate");
		user.setStatus(StatusEnum.ENABLE);
		userMpper.insert(user);
		
		User userUpd = userMpper.queryOne(user.getId());
		userUpd.setName("更新name");
		userMpper.update(userUpd);
		
		Assert.assertEquals("更新失败",userUpd.getName(), userMpper.queryOne(user.getId()).getName());
	}
	
	@Test
	public void testParamSelect() {
		String code = "002";
		List<User> list = userMpper.queryByParams(code);
		
		log.info("查询编码为002,查询结果为：{}条,结果集为：{}",list.size(), Arrays.toString(list.toArray()));
	}

}
```

运行测试用例后，就可以看见效果了。

![](http://qiniu.xds123.cn/18-12-2/55568488.jpg)

具体控制台输出就输出了，可下载源码自行测试下。

### xml方式

0.配置xml版的mapper。
```java
/**
 * xml映射
 * @author oKong
 *
 */
public interface UserXmlMapper {

	User queryOne(Long id);
	
	int insert(User user);
	
	void update(User user);
	
	void delete(Long id);
		
	List<User> queryByParams(@Param("code")String code);
}
```

没啥区别，就是讲sql语句放入到了xml中进行编写而已。

1.编写mapper.xml配置映射文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.lqdev.learning.springboot.chapter35.biz.mapper.UserXmlMapper">
   
	<!-- 配置返回类型 -->
   <resultMap type="User" id="userResultMap">
      <result column="id" property="id"/>
      <result column="code" property="code"/>
      <result column="name" property="name"/>
      <result column="status" property="status"/>
      <result column="gmt_create" property="gmtCreate" jdbcType="DATE"/>
      <result column="gmt_modified" property="gmtModified" jdbcType="DATE"/>
   
   </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        id, code, name, status, gmt_create, gmt_modified
    </sql>
    
    <select id="queryOne" resultMap="userResultMap">
      select 
      <include refid="Base_Column_List"></include>
      from user
      where id = #{id}
    </select>

    <!-- 返回主键id -->
    <insert id="insert" parameterType="User" keyProperty="id" useGeneratedKeys="true">
        insert into user(code,name,status) values(#{code},#{name}, #{status})
        <!--  insert into user(code,name,status) values(#{code},#{name}, #{status, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}) -->
    </insert>
    
    <update id="update"  parameterType="User">
       update user set code=#{code}, name = #{name}, status = #{status} where id=#{id}
    </update>
    
    <delete id="delete">
       delete from user where id=#{id}
    </delete>
    
    <select id="queryByParams" resultMap="userResultMap">
      select 
      <include refid="Base_Column_List"></include>
      from user
       where code = #{code}
    </select>
</mapper>
```
2.mybatis配置文件。

`mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--全局配置-->
    <settings>
        <!-- 这个配置使全局的映射器启用或禁用缓存 -->
        <setting name="cacheEnabled" value="true"/>
        <!-- 全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载 -->
        <setting name="lazyLoadingEnabled" value="false"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="defaultExecutorType" value="REUSE"/>
        <setting name="defaultStatementTimeout" value="25000"/>
        <setting name="aggressiveLazyLoading" value="true"/>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    <typeHandlers>
       <!-- 枚举类 -->
       <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
         javaType="cn.lqdev.learning.springboot.chapter35.biz.entity.StatusEnum"/>
    </typeHandlers>
    
</configuration>
```

对于特殊的类型，可以通过`typeHandlers`进行配置。稍后章节也会讲解想通过其他的方式进行配置。

3.创建xml方式配置文件：`application-xml.properties`，配置xml和config的路径地址

```
# 配置mapper.xml和mybatis-config.xml路径
mybatis.config-location=classpath:mybatis/mybatis-config.xml
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

4.编写测试类：`UserXmlMapperTest.java`
此类和`UserMapperTest`类似的，唯一区别就是制定了运行环境变量为：xml
```java
@ActiveProfiles("xml")
```


### 枚举类型处理器配置
>在实体对象中我们设置了枚举类型:`StatusEnum`。在`mybatis`中对于枚举的默认配置是由`EnumTypeHandler`处理类进行处理的，其会默认使用`name`进行赋值。同时`mybatis`还提供了一个`EnumOrdinalTypeHandler`处理类，其是根据枚举的索引值进行赋值的。

注册类型处理类有很多中方式，但每一种方式最后都是通过`TypeHandlerRegistry`类进行处理的，这里讲解下通过多种方式进行配置。

- 配置文件中新增属性：`mybatis.type-handlers-package`：配置处理类的路径。
```
# 类型处理类
mybatis.type-handlers-package=cn.lqdev.learning.springboot.chapter35.config
```

处理类示例：`CustomEnumOrdinalTypeHandler.java`。这里直接继承`EnumOrdinalTypeHandler`进行自定义。

```java
/**
 * 
 * @author oKong
 *
 */
//枚举索引处理类
@MappedTypes(value = { StatusEnum.class })
public class CustomEnumOrdinalTypeHandler<E extends Enum<E>> extends EnumOrdinalTypeHandler<E>{

	public CustomEnumOrdinalTypeHandler(Class<E> type) {
		super(type);
	}
}
```
其中，`@MapperType`指定了哪些类是指定此处理类的。

- 自定义`ConfigurationCustomizer`类进行配置（推荐）。
```java
	/**
	 * 
	 * <p>函数名称：  ConfigurationCustomizer      </p>
	 * <p>功能说明： 自定义相关注册器
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @return
	 *
	 * @date   创建时间：2018年12月2日
	 * @author 作者：oKong
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		ConfigurationCustomizer config = new ConfigurationCustomizer() {
			
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
//				TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
				// mapper接口注册器
//				MapperRegistry mapperRegistry = configuration.getMapperRegistry();
				// 类型处理器
				TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
				typeHandlerRegistry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
			}
		};
		
		return config;
	}
```

- 通过`SqlSessionFactory`来获取`TypeHandlerRegistry`进行配置。
```java
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@PostConstruct
	public void registerTypeHandler() {
		TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
		registry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
	}
```

**以上三种都可以进行相关类型的处理类配置，建议直接使用第二种。**

完整的`MybatisConfig`类：

```java
/**
 * mybaits配置
 * @author oKong
 *
 */
@Configuration
@MapperScan("cn.lqdev.learning.springboot.chapter35.biz.mapper")//mapper地址
public class MybatisConfig {
	
	//使用 SqlSessionFactory 类获取 TypeHandlerRegistry 进行注册
//	@Autowired
//	SqlSessionFactory sqlSessionFactory;
//	
//	@PostConstruct
//	public void registerTypeHandler() {
//		TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
//		registry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
//	}
	
	/**
	 * 
	 * <p>函数名称：  ConfigurationCustomizer      </p>
	 * <p>功能说明： 自定义相关注册器
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @return
	 *
	 * @date   创建时间：2018年12月2日
	 * @author 作者：oKong
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		ConfigurationCustomizer config = new ConfigurationCustomizer() {
			
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
//				TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
				// mapper接口注册器
//				MapperRegistry mapperRegistry = configuration.getMapperRegistry();
				// 类型处理器
				TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
				typeHandlerRegistry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
			}
		};
		
		return config;
	}
}
```


## 参考资料
1. [http://www.mybatis.org/mybatis-3/zh/](http://www.mybatis.org/mybatis-3/zh/ "http://www.mybatis.org/mybatis-3/zh/")

2. [http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html "http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html")

## 总结
>本章节主要简单介绍了`mybatis`集成的两种模式。两种模式各有特点，注解版适合简单快速的模式，而xml方式适合相对复杂的sql语句，写在xml中，可以进行统一修改，而不需要去修改java代码。对于原生使用`mybatis`而言，感觉也是比较简单的，就是写起语句比较麻烦，都需要手动去编写。所以还是选择一个脚手架吧，`mybatis-plus`是一个很好的选择。当然了其他的脚手架框架了，大家可自行根据实际情况进行抉择。前几天去看博客评论时，有人觉得**开发本来就很累了还要学习这种小框架**，我觉得吧，这些小框架可以节省多少繁琐的工作呀。让开发人员可以专注于业务代码，多美好的一件事情呀。将近两星期没有写，可能写的有些乱了，大家可以直接下载源码示例进行查看下，原本想分开两个工程进行讲解，感觉也没有必要，就合并在一起了，通过不同的环境配置进行切换，还请谅解呀！

## 最后
>目前互联网上很多大佬都有`SpringBoot`系列教程，如有雷同，请多多包涵了。**原创不易，码字不易**，还希望大家多多支持。若文中有所错误之处，还望提出，谢谢。

## 老生常谈
- 个人QQ：`499452441`
- 微信公众号：`lqdevOps`

![公众号](http://qiniu.xds123.cn/default/wxgzh8cm.jpg)

个人博客：[http://blog.lqdev.cn](http://blog.lqdev.cn "http://blog.lqdev.cn")

完整示例：[https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35](https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35 "https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35")

