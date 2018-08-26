### 缘起
>前段时间公司领导叫编写一两课关于`springboot`的基础知识培训课程，说实话，也是今年年初才开始接触了SpringBoot这个脚手架，使用了之后才发现打开了一个新世界。再之后也没有一些系统的学习过springboot，刚好借着这次编写培训教程机会，以一个初学者的身份，希望能够对sproingboot进行一次完整的学习。也希望通过此系列教程，能够加深对`springboot`的理解，之后也会编写关于`springcloud`的系列教程。也希望，通过系列教程的编写，能提供自己的写作水平（题外话：只从大学毕业后，就没有正经的写过文章了，很是遗憾。）

### 开发环境说明
>在开发IDE选择上，选择了spring官方退出的sts版本，本人未使用过`intellij idea`（可能落伍了，⊙﹏⊙‖∣）。

- java 8 
- spring boot: 1.5.14.RELEASE
- 开发IDE：Spring Tool Suite(3.9.3.RELEASE)
- maven: 3.0.3

### 教程安排
>由于自身能力有限，本着简单入门的原则，并结合了工作中碰见的一些常见场景和问题，将工作中常用知识点进行编写，同时也会穿插一些能提供开发效率的第三方包或者工具的介绍和简单实例。

-  [第一章：第一个Springboot应用](http://blog.lqdev.cn/2018/07/11/springboot/chapter-one/ "第一章：第一个Springboot应用")
-  [第二章：lombok介绍及简单使用](http://blog.lqdev.cn/2018/07/12/springboot/chapter-two/ "第二章：lombok介绍及简单使用")
-  [第三章：springboot配置详解](http://blog.lqdev.cn/2018/07/14/springboot/chapter-third/ "第三章：springboot配置详解")
-  [第四章：日志配置](http://blog.lqdev.cn/2018/07/15/springboot/chapter-four/ "第四章：日志配置")
-  [第五章：多环境配置](http://blog.lqdev.cn/2018/07/15/springboot/chapter-five/ "第五章：多环境配置")
-  [第六章：常用注解介绍及简单使用](http://blog.lqdev.cn/2018/07/16/springboot/chapter-six/)
-  [第七章：过滤器、监听器、拦截器](http://blog.lqdev.cn/2018/07/19/springboot/chapter-seven/)
-  [第八章：统一异常处理](http://blog.lqdev.cn/2018/07/20/springboot/chapter-eight/)
-  [第九章：Mybatis-plus的集成和使用](http://blog.lqdev.cn/2018/07/21/springboot/chapter-nine/)
-  [第十章：Swagger2集成](http://blog.lqdev.cn/2018/07/21/springboot/chapter-ten/)
-  [第十一章：Redis的集成和使用](http://blog.lqdev.cn/2018/07/23/springboot/chapter-eleven/)
-  [第十二章：RabbitMQ的集成和使用](http://blog.lqdev.cn/2018/07/24/springboot/chapter-twelve/)
-  [第十三章：测试相关（单元测试、性能测试）](http://blog.lqdev.cn/2018/07/26/springboot/chapter-thirteen/)
-  [第十四章：基于docker的简单部署](http://blog.lqdev.cn/2018/07/27/springboot/chapter-fourteen/)
-  [第十五章：基于Postman的RESTful接口测试](http://blog.lqdev.cn/2018/07/28/springboot/chapter-fifteen/)
-  [第十六章：web应用开发](http://blog.lqdev.cn/2018/08/07/springboot/chapter-sixteen/)
-  [第十七章：web应用开发之文件上传](http://blog.lqdev.cn/2018/08/07/springboot/chapter-seventeen/)
-  [第十八章：web应用开发之WebJars使用](http://blog.lqdev.cn/2018/08/08/springboot/chapter-eighteen/ "第十八章：web应用开发之WebJars使用]")
-  [第十九章：web应用开发之WebSocket](http://blog.lqdev.cn/2018/08/14/springboot/chapter-nineteen/ "第十九章：web应用开发之WebSocket")
-  [第二十章：异步开发之异步请求](http://blog.lqdev.cn/2018/08/16/springboot/chapter-twenty/ "第二十章：异步开发之异步请求")
-  [第二十一章：异步开发之异步调用](http://blog.lqdev.cn/2018/08/17/springboot/chapter-twenty-one/ "第二十一章：异步开发之异步调用")
-  [第二十二章：定时任务](http://blog.lqdev.cn/2018/08/19/springboot/chapter-twenty-two/ "第二十二章：定时任务")
-  [第二十三章：日志管理之整合篇](http://blog.lqdev.cn/2018/08/22/springboot/chapter-twenty-three/ "第二十三章：日志管理之整合篇")
-  [第二十四章：日志管理之AOP统一日志](http://blog.lqdev.cn/2018/08/24/springboot/chapter-twenty-four/ "第二十四章：日志管理之AOP统一日志")
-  [第二十五章：日志管理之自定义Appender](http://blog.lqdev.cn/2018/08/25/springboot/chapter-twenty-five/ "第二十五章：日志管理之自定义Appender")
-  待补充......

番外：

- [SpringBoot | 番外：使用小技巧合集](http://blog.lqdev.cn/2018/08/11/springboot/springboot-tips/)
- [Mybatis-Plus使用全解](http://blog.lqdev.cn/2018/08/06/%E6%97%A5%E5%B8%B8%E7%A7%AF%E7%B4%AF/mybatis-plus-guide-one/)
- [关于@webFilter使用@Order无效问题](http://blog.lqdev.cn/2018/08/26/%E6%97%A5%E5%B8%B8%E7%A7%AF%E7%B4%AF/correct-webfilter/)

### 老生常谈
- 个人QQ：`499452441`
- 公众号：`lqdevOps`

![](http://qiniu.xds123.cn/18-7-8/72146435.jpg)

个人博客：[http://blog.lqdev.cn](http://blog.lqdev.cn "http://blog.lqdev.cn") 

本文地址：http://blog.lqdev.cn/2018/07/11/springboot/chapter-zero/
