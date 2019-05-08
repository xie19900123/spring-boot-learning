package cn.lqdev.learning.chapter37;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment;

import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：JasyptApplication 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年5月8日
* @author      创建人：oKong
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年5月8日   oKong   创建该类功能。
*
***********************************************************************
*</p>
*/
@SpringBootApplication
@Slf4j
public class JasyptApplication {
	
	public static void main(String[] args) throws Exception {

//		SpringApplication.run(JasyptApplication.class, args);
		//使用自定义环境变量 实现一些特殊场景下的加密字符解密操作
		//若无额外的xml引入文件需要解密时，可直接使用SpringApplication.run(JasyptApplication.class, args);即可
		//若想在引入的xml中使用，需要加入环境变量，如以下模式
		new SpringApplicationBuilder().environment(new StandardEncryptableEnvironment())
		.sources(JasyptApplication.class).run(args);
		log.info("spring-boot-jasypt-chapter37服务启动!");
	}
}
