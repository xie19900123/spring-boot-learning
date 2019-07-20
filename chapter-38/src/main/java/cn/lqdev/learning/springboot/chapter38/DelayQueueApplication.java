package cn.lqdev.learning.springboot.chapter38;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：ApplicationChapter38 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年7月17日
* @author      创建人：xds
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年7月17日   xds   创建该类功能。
*
***********************************************************************
*</p>
*/
@SpringBootApplication
@Slf4j
public class DelayQueueApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DelayQueueApplication.class, args);
		log.info("spring-boot-rabbitmq-delay-queue-chapter38服务启动!");
	}

}
