package cn.lqdev.learning.springboot.chapter35.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import cn.lqdev.learning.springboot.chapter35.biz.entity.User;

/**
 * 注解配置
 * @author okong
 *
 */
public interface UserMapper {

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
