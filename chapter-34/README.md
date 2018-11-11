## 前言
>上一章节，讲解了如何使用`Spring-WS`构建`WebService`服务。其实，创建`WebService`的方式有很多的，今天来看看如何使用`apache cxf`来构建及调用`WebService`服务。

*   [前言](#前言)
*   [一点知识](#一点知识)
    *   [何为Apache-CXF](#何为Apache-CXF)
    *   [关于JAX-WS规范](#关于JAX-WS规范)
        *   [常用注解介绍](#常用注解介绍)
*   [SpringBoot整合CXF实例](#SpringBoot整合CXF实例)
    *   [服务端构建](#服务端构建)
    *   [客户端调用](#客户端调用)
        *   [异常捕获](#异常捕获)
    *   [自定义拦截器](#自定义拦截器)
        *   [服务端拦截器](#服务端拦截器)
        *   [客户端拦截器](#客户端拦截器)
*   [参考资料](#参考资料)
*   [总结](#总结)
*   [最后](#最后)
*   [老生常谈](#老生常谈)

## 一点知识
### 何为Apache-CXF
>`Apache CXF`是一个开源的`Services`框架，`CXF`帮助您利用`Frontend`编程 API 来构建和开发Services，像`JAX-WS`、`JAX-RS`。这些`Services`可以支持多种协议，比如：`SOAP`、`XML/HTTP`、`RESTful HTTP `或者`CORBA`，并且可以在多种传输协议上运行，比如：`HTTP`、`JMS` 或者`JBI`，**CXF大大简化了 Services 的创建，同时它可以天然地和Spring进行无缝集成**。

以下是官网给出的介绍：https://github.com/apache/cxf

![](http://qiniu.xds123.cn/18-11-10/4837068.jpg)

最常用的是使用`cxf`开发`web-service`。本身是基于`JAX-WS`规范来实现的。当然，本身`CXF`也实现了`JAX-RS`规范来实现`RESTFul Service`。

### 关于JAX-WS规范
>`JAX-WS`全称：`Java API for XML-Based Web Services`。`JAX-WS`是一种编程模型，它通过支持将基于注释的标准模型用于开发` Web Service `应用程序和客户机来简化应用程序开发。

`JAX-WS`是Java程序设计语言一个用来创建Web服务的API。
- 在服务器端，用户只需要通过`Java`语言定义远程调用所需要实现的接口`SEI（service endpoint interface）`，并提供相关的实现，通过调用`JAX-WS`的服务发布接口就可以将其发布为`WebService`接口。
- 在客户端，用户可以通过`JAX-WS`的API创建一个代理（用本地对象来替代远程的服务）来实现对于远程服务器端的调用。当然`JAX-WS`也提供了一组针对底层消息进行操作的API调用，你可以通过`Dispatch`直接使用SOAP消息或XML消息发送请求或者使用Provider处理SOAP或XML消息。

#### 常用注解介绍
>`JAX-WS`提供了一系列的注解，可以对`WebService`的接口规范化。以下介绍下最常用的几个注解。

- **@WebService**：用于将Java类标记为实现` Web Service `或者将服务端点接口 (SEI) 标记为实现`Web Service`接口。
其包含的属性有：
  - name：此属性的值包含XML Web Service的名称。在默认情况下，该值是实现XML Web Service的类的名称，**wsdl:portType **的名称。缺省值为 Java 类的**简单名称 + Service**。（字符串）
  - targetNamespace：默认的值为 "http://包名/" ，可以通过此变量指定一个自定义的targetNamespace值。
  - serviceName：对外发布的服务名，指定` Web Service `的服务名称：wsdl:service。缺省值为 Java 类的简单名称 + Service。（字符串）
  - endpointInterface：
  - portName：**wsdl:portName**的值。缺省值为`WebService.name+Port`
  - wsdlLocation：指定用于定义 Web Service 的 WSDL 文档的 Web 地址 
  
  
- **@WebMethod**：表示作为一项`Web Service`操作的方法。仅支持在使用`@WebService `注解的类上使用`@WebMethod`注解。
  - **operationName**：指定与此方法相匹配的wsdl:operation 的名称。缺省值为 Java 方法的名称。（字符串）
  - **action**：定义此操作的行为。对于 SOAP 绑定，此值将确定 SOAPAction 头的值。缺省值为 Java 方法的名称。（字符串）
  - **exclude**：指定是否从 Web Service 中排除某一方法。缺省值为 false。（布尔值）
    
    
- **@WebParam**：用于定制从单个参数至`Web Service`消息部件和`XML`元素的映射。

其他注解，可以查看：[WebService注解总结](https://blog.csdn.net/w410589502/article/details/51742004 "WebService注解总结")

为了有个直观感受，大家可以看看以下这个wsdl文件，对应以上各注解属性的值(加了前缀`oKong`)。

```java
//@WebService 属性示例
@WebService(targetNamespace = 'http://www.lqdev.cn/webservice' ,name = "oKongName", serviceName="oKongServiceName", portName = "oKongPortName",endpointInterface="cn.lqdev.learning.springboot.cxf.service.AuthorService")

//@webMethod @WebParam 常用属性示例
@WebMethod(operationName="oKongOperationName",action="oKongAction")
String getAuthorName(@WebParam(name = "paramName") String name);
```
![](http://qiniu.xds123.cn/18-11-10/45706268.jpg)

标记的有点花，⊙﹏⊙‖∣。大家可以自己对照下。

## SpringBoot整合CXF实例
>接下来，我们以一个简单的示例来演示下，如何发布服务及如何进行服务调用。

### 服务端构建
创建一个工程：`spring-boot-cxf-service`.

0.引入CXF的POM文件
```xml
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
            <version>3.2.5</version>
        </dependency>
```
1.创建实体，按`JAX-WS`规范，创建接口及其实现类。
`AuthorDto.java`

```java
/**
 * 作者信息实体
 * @author oKong
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    String name;
    
    List<String> hobby; 
    
    String birthday;
    
    String description;
    
    Sex sex;
}
```

`Sex.java`性别枚举类
```java
/**
 * 性别枚举类
 * @author oKong
 *
 */
public enum Sex {

    MALE("male"),
    FEMALE("female");
    
    String value;
    
    Sex(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }

    public static Sex fromValue(String v) {
        for (Sex c : Sex.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }    
}
```
`AuthorService.java`接口类 

```java
/**
 * 创建服务接口
 * @author oKong
 *
 */
@WebService(targetNamespace = WsConst.NAMESPACE_URI ,name = "authorPortType")
public interface AuthorService {

    /**
     * 根据名称获取作者信息
     * @author 作者：oKong
     */
    @WebMethod(operationName="getAuthorByName")
    AuthorDto getAuthor(@WebParam(name = "authorName") String name);

    /**
     * 获取作者列表信息
     * @author oKong
     */
    @WebMethod
    List<AuthorDto> getAuthorList();
    
    /**
     * 返回字符串测试
     * @author oKong
     */
    String getAuthorString(@WebParam(name = "authorName")String name);
}
```

`AuthorServiceImpl.java`接口实现类

```java
@WebService(
         targetNamespace = WsConst.NAMESPACE_URI, //wsdl命名空间 
         name = "authorPortType",                 //portType名称 客户端生成代码时 为接口名称
         serviceName = "authorService",           //服务name名称
         portName = "authorPortName",             //port名称
         endpointInterface = "cn.lqdev.learning.springboot.cxf.service.AuthorService")//指定发布webservcie的接口类，此类也需要接入@WebService注解
public class AuthorServiceImpl implements AuthorService{

    @Override
    public AuthorDto getAuthor(String name) {
        AuthorDto author = new AuthorDto();
        author.setBirthday("1990-01-23");
        author.setName("姓名：" + name);
        author.setSex(Sex.MALE);
        author.setHobby(Arrays.asList("电影","旅游"));
        author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
        return author;
    }

    @Override
    public List<AuthorDto> getAuthorList() {
        List<AuthorDto> resultList = new ArrayList<>();
        AuthorDto author = new AuthorDto();
        author.setBirthday("1990-01-23");
        author.setName("姓名：oKong");
        author.setSex(Sex.MALE);
        author.setHobby(Arrays.asList("电影","旅游"));
        author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
        resultList.add(author);
        resultList.add(author);
        return resultList;
    }

    @Override
    public String getAuthorString(String name) {
        AuthorDto author = getAuthor(name);
        return author.toString();
    }
}
```
注意：相关注解可以查看章节：[常用注解介绍](#常用注解介绍)

主要是接口实现类的`@WebService`对应属性值都要wsdl文件的映射关系。
```java
@WebService(
         targetNamespace = WsConst.NAMESPACE_URI, //wsdl命名空间 
         name = "authorPortType",                 //portType名称 客户端生成代码时 为接口名称
         serviceName = "authorService",           //服务name名称
         portName = "authorPortName",             //port名称
         endpointInterface = "cn.lqdev.learning.springboot.cxf.service.AuthorService")//指定发布webservcie的接口类，此类也需要接入@WebService注解

```
2.创建常量类，配置类，设置访问uri路径等。

`WsConst.java`

```java
/**
 * 常量类
 * @author oKong
 *
 */
public class WsConst {
    public static final String NAMESPACE_URI = "http://www.lqdev.cn/webservice";
}
```
`CxfWebServiceConfig.java`

```java
/**
 * cxf配置类
 * @author oKong
 *
 */
@Configuration
public class CxfWebServiceConfig {
    
    //这里需要注意  由于springmvc 的核心类 为DispatcherServlet
    //此处若不重命名此bean的话 原本的mvc就被覆盖了。可查看配置类：DispatcherServletAutoConfiguration
    //一种方法是修改方法名称 或者指定bean名称 
    //这里需要注意 若beanName命名不是 cxfServletRegistration 时，会创建两个CXFServlet的。
    //具体可查看下自动配置类：Declaration org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
    //也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet() {
        //注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }
    
    /**
     * 申明业务处理类 当然也可以直接 在实现类上标注 @Service
     * @author oKong
     */
    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl();
    }
    
    /*
     * 非必要项
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        return springBus;
    }
    
    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint(AuthorService authorService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), authorService);
        endpoint.publish("/author");//发布地址
        return endpoint;
    }
}
```
**注意事项：**
- 配置`ServletRegistrationBean`时，需要注意设置方法的名称或者bean的名称时，不要和默认的`DispatcherServlet`类重名了，会导致原先的`mvc`接口无法使用，因为被覆盖了。
- 修改访问的路径可以通过设置`ServletRegistrationBean`来修改，但同时，要注意需要设置bean的名称为`cxfServletRegistration`，不然会造成注册多个`CXFServlet`的。具体原因可查看自动配置类：`org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration`。

![CxfAutoConfiguration](http://qiniu.xds123.cn/18-11-10/16808447.jpg)

所以，**修改访问路径还可以通过配置项：`cxf.path`来设置。其默认的访问url为：`/services`**

3.创建启动类，同时启动应用。
```java
/**
 * cxf服务发布示例
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class CxfServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CxfServiceApplication.class, args);
        log.info("spirng-boot-cxf-service-chapter34启动!");
    }
}
```
启动后，可以从控制台看见可以访问的url路径信息。
```
2018-11-10 22:06:40.898  INFO 46364 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'CXFServlet' to [/ws/*]
2018-11-10 22:06:40.899  INFO 46364 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
```

访问：http://127.0.0.1:8080/ws/author?wsdl ，验证是否发布成功。

![author](http://qiniu.xds123.cn/18-11-10/79105494.jpg)

自此，`webService`发布成功了。

### 客户端调用
创建一个客户端工程：`spring-boot-cxf-client`。

0.引入cxf依赖。
```xml
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
            <version>3.2.5</version>
        </dependency> 
```
1.创建`wsdl`文件，同时利用插件：`cxf-codegen-plugin`创建相关类。
```xml
<!-- cxf-codegen-plugin -->
      <plugin>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-codegen-plugin</artifactId>
        <version>3.2.5</version>
        <executions>
          <execution>
            <id>generate-sources</id>
            <phase>generate-sources</phase>
            <configuration>
              <sourceRoot>${project.build.directory}/generated/cxf</sourceRoot>
              <wsdlOptions>
                <wsdlOption>
                  <wsdl>src/main/resources/wsdl/author.wsdl</wsdl>
                  <wsdlLocation>classpath:wsdl/author.wsdl</wsdlLocation>
                </wsdlOption>
              </wsdlOptions>
            </configuration>
            <goals>
              <goal>wsdl2java</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

**将`wsdl`文件，放入`main/resources/wsdl`目录下。之后执行：`mvn generate-sources`命令，就会自动创建相应的类文件了。拷贝相应的类文件至`src/java`目录下即可。或者直接指定`sourceRoot`也是可以的。**

![](http://qiniu.xds123.cn/18-11-10/92599225.jpg)

2.创建调用的配置类，这里演示两种方式。

`WsConst.java`

```java
/**
 * 常量类
 * @author oKong
 *
 */
public class WsConst {
    public static final String NAMESPACE_URI = "http://www.lqdev.cn/webservice";
    public static final String SERVICE_ADDRESS= "http://127.0.0.1:8080/ws/author?wsdl";
}
```

`CxfClinetConfig.java`

```java
/**
 * 配置类
 * 
 * @author oKong
 *
 */
@Configuration
public class CxfClientConfig {

    
    /**
     *  以接口代理方式进行调用 AuthorPortType接口
     */
    @Bean("cxfProxy")
    public AuthorPortType createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(AuthorPortType.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.SERVICE_ADDRESS);//服务地址：http://127.0.0.1:8080/ws/autho

        return (AuthorPortType) jaxWsProxyFactoryBean.create();
    }
    
    /*
     * 采用动态工厂方式 不需要指定服务接口
     */
    @Bean
    public Client createDynamicClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WsConst.SERVICE_ADDRESS);
        return client;
    } 
}
```
注意：除了使用`JaxWsProxyFactoryBean`和`JaxWsDynamicClientFactory`调用外，还可以直接使用自动生成的`AuthorService`类直接调用的，此类继承至`javax.xml.ws.Service`。
如：
```java
    /*
     * 直接调用
     */
    @Bean("jdkProxy")
    public AuthorPortType createJdkService() {
        AuthorService authorService = new AuthorService();
        return authorService.getAuthorPortName();
    }
```
其实，最后都是使用`AuthorPortType`进行调用的。

3.创建控制层，进行调用示例。
```java
/**
 * 调用示例
 * @author oKong
 *
 */
@RestController
@RequestMapping("/cxf")
public class DemoController {

    @Autowired
    Client client;
    
    @Autowired
    @Qualifier("cxfProxy")
    AuthorPortType authorPort;
    
    @GetMapping("/getauthorstring")
    public String getAuthorString(String authorName) {
        return authorPort.getAuthorString(authorName);
    }
    
    @GetMapping("/getauthor")
    public AuthorDto getAuthor(String authorName) {
        return authorPort.getAuthorByName(authorName);
    }
    
    @GetMapping("/getauthorlist")
    public List<AuthorDto> getAuthorList() {
        return authorPort.getAuthorList();
    }
    
    @GetMapping("/dynamic/{operation}")
    public Object getAuthorStringByDynamic(@PathVariable("operation")String operationName, String authorName) throws Exception {
        //这里就简单的判断了 
        Object[] objects = null; 
//        client.getEndpoint().getBinding().getBindingInfo().getOperations()
        if ("getAuthorList".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName);
        } else if ("getAuthorString".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else if ("getAuthorByName".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else {
            throw new RuntimeException("无效的调用方法");
        }
        return objects != null && objects.length > 0 ? objects[0] : "返回异常";
    }    
}
```

4.编写启动类，同时制定应用端口为：8090。
```java
/**
 * cxf-客户端调用示例
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class CxfClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CxfClientApplication.class, args);
        log.info("spring-boot-cxf-client-chapter34启动!");
    }
}
```

端口号配置：
```
server.port=8090
```

5.启动应用，依次访问。查看是否调用成功。

http://127.0.0.1:8090/cxf/getauthorstring?authorName=oKong

![](http://qiniu.xds123.cn/18-11-11/23849909.jpg)

http://127.0.0.1:8090/cxf//getauthorlist?authorName=oKong

![](http://qiniu.xds123.cn/18-11-10/59069735.jpg)

动态工厂方式调用：
http://127.0.0.1:8090/cxf/dynamic/getAuthorList?authorName=oKong
![](http://qiniu.xds123.cn/18-11-10/30214354.jpg)

其他的就不一一贴图了，可自行访问下。

#### 异常捕获
`Cxf`发生异常时，会统一抛出：`org.apache.cxf.interceptor.Fault`类的，所以想要捕获异常，可以在统一异常里面进行捕获，关于统一异常处理，可以查看文章：[第八章：统一异常、数据校验处理](http://blog.lqdev.cn/2018/07/20/springboot/chapter-eight/ "第八章：统一异常、数据校验处理")。

### 自定义拦截器
CXF的拦截器分为两种：`InInterceptor`和`OutInterceptor`。显而易见，`InInterceptor`可以处理**soap请求消息**，`OutInterceptor`可以处理**soap响应消息**。其拦截器都继承至`AbstractPhaseInterceptor<Message>`接口类，而且，本身也自带了很多的拦截器，可以自行添加看看，比如日志拦截器之类的：`LoggingInInterceptor`和`LoggingOutInterceptor`。

![](http://qiniu.xds123.cn/18-11-10/22071800.jpg)

请求流程图：

![](http://qiniu.xds123.cn/18-11-10/67882812.jpg)

拦截器链的阶段：

 **输入拦截器链**有如下几个阶段，这些阶段按照在拦截器链中的先后顺序排列。
 
![](http://qiniu.xds123.cn/18-11-10/52675801.jpg)

**输出拦截器链**有如下几个阶段，这些阶段按照在拦截器链中的先后顺序排列。

![](http://qiniu.xds123.cn/18-11-10/94131373.jpg)

具体名称，可查看：`org.apache.cxf.phase.Phase`。

![](http://qiniu.xds123.cn/18-11-10/17838454.jpg)

现在，我们自定义个实现拦截器，实现请求时header需要带上特定参数，或者大家可不写一些安全校验的自定义拦截器，本例只是简单的示例。
#### 服务端拦截器
1.检验拦截器：`CheckAuthInterceptor.java`

```java
/**
 * 简易-安全校验拦截器
 * 
 * @author oKong
 *
 */
@Slf4j
public class CheckAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public CheckAuthInterceptor() {
        super(Phase.PRE_INVOKE);// 拦截节点：调用之前
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        log.info("检验拦截器开始检验：{}", message);
        // 处理方法
        List<Header> headers = message.getHeaders();

        // 判断是否存header
        // 检查headers是否存在
        if (headers == null | headers.size() < 1) {
            throw new Fault(new IllegalArgumentException("验证失败，请传入正确参数(40001)"));//可自定义编码规范
        }
        //取出header
        Header header = headers.get(0);
        //获取对象
        Element element = (Element) header.getObject();//这里获取的就时 auth对象了
        NodeList tokenNode = element.getElementsByTagName("token");
        if(tokenNode == null || tokenNode.getLength() < 1) {
            //无token节点
            throw new Fault(new IllegalArgumentException("验证失败，请传入正确参数(40002)"));//自定义编码规范
        }
        //获取token
        String token = tokenNode.item(0).getTextContent();
        log.info("请求的token为：{}", token);
        //这里可以对token 有效性进行判断
    }
}
```
2.Endpoint中加入拦截器配置。
```java
    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint(AuthorService authorService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), authorService);
        endpoint.publish("/author");//发布地址
        endpoint.getInInterceptors().add(createCheckAuthInterceptor());//加入拦截器
//        endpoint.getOutInterceptors().add()//响应拦截器
        return endpoint;
    }
    
    @Bean
    public Interceptor<SoapMessage> createCheckAuthInterceptor(){
        return new CheckAuthInterceptor();
    }
```

#### 客户端拦截器

1.编写拦截器。

```java
/**
 * 简易-安全校验拦截器
 * @author oKong
 *
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage>{
    
    public AuthInterceptor() {
        super(Phase.PREPARE_SEND);//准备请求时进行拦截
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        //处理方法
        List<Header> headers = message.getHeaders();
        
        Document doc = DOMUtils.createDocument();
        Element element = doc.createElement("auth");
        Element tokenEle = doc.createElement("token");
        tokenEle.setTextContent(UUID.randomUUID().toString());
        element.appendChild(tokenEle);
        //这里需要注意 默认情况下 是使用 org.w3c.dom.Element对象设置对象值的。
        //也可以指定 DataBinding 设置对象的。可继承抽象类： org.apache.cxf.databinding.AbstractDataBinding
        //具体源码可查看:org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor
        Header tokenHeader = new SoapHeader(new QName(""), element);
//        tokenHeader.setDataBinding()
        headers.add(tokenHeader);
    }

}
```
**这里需要注意：**
- 设置header时，默认是`org.w3c.dom.Element`对象。
- 自定义对象时，可设置`DataBinding`类来解析（未尝试，只是看了一眼源码，里面有此逻辑，有兴趣的同学可以自行试试）。

2.请求类中加入拦截器。
`CxfClientConfig.java`

```java
    /**
     *  以接口代理方式进行调用 AuthorPortType接口
     */
    @Bean("cxfProxy")
    public AuthorPortType createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(AuthorPortType.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.SERVICE_ADDRESS);//服务地址：http://127.0.0.1:8080/ws/autho
        jaxWsProxyFactoryBean.getOutInterceptors().add(createInterceptor());//加入自定义拦截器
        return (AuthorPortType) jaxWsProxyFactoryBean.create();
    }
    
    /*
     * 采用动态工厂方式 不需要指定服务接口
     */
    @Bean
    public Client createDynamicClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WsConst.SERVICE_ADDRESS);
        client.getOutInterceptors().add(createInterceptor());
        return client;
    }
    
    @Bean
    public Interceptor<SoapMessage> createInterceptor() {
        return new AuthInterceptor();
    }
```

重新启动后，再次请求就可以看见相关日志输出了，可以试着不设置token，看看有拦截。

![正常请求](http://qiniu.xds123.cn/18-11-10/43308435.jpg)

异常请求：

![异常请求](http://qiniu.xds123.cn/18-11-10/54892013.jpg)

## 参考资料
1. [http://cxf.apache.org/docs/springboot.html](http://cxf.apache.org/docs/springboot.html "http://cxf.apache.org/docs/springboot.html")

2. [https://www.code996.cn/post/2017/cxf-interceptor/](https://www.code996.cn/post/2017/cxf-interceptor/ "https://www.code996.cn/post/2017/cxf-interceptor/")
## 总结
>本章节主要简单介绍了`apache-cxf`的使用。这文章示例写下来，我发现比`spring-ws`更简单呀，也更让人容易理解、逻辑比较清晰，而且也能设置一些差异化的东西。不知道是不是真的对`spring-ws`了解的不够呀，没有发现`spring-ws`的优点呀。自此，关于`WebService`的文章就暂时告一段落了。

## 最后
>目前互联网上很多大佬都有`SpringBoot`系列教程，如有雷同，请多多包涵了。**原创不易，码字不易**，还希望大家多多支持。若文中有所错误之处，还望提出，谢谢。

## 老生常谈
- 个人QQ：`499452441`
- 微信公众号：`lqdevOps`

![公众号](http://qiniu.xds123.cn/default/wxgzh8cm.jpg)

个人博客：[http://blog.lqdev.cn](http://blog.lqdev.cn "http://blog.lqdev.cn")

完整示例：[https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-34](https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-34 "https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-34")

原文地址：[http://blog.lqdev.cn/2018/11/11/springboot/chapter-thirty-four/](http://blog.lqdev.cn/2018/11/11/springboot/chapter-thirty-four/)