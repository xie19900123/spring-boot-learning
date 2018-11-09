package cn.lqdev.learning.springboot.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * web-service 简单示例
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class WebServiceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebServiceApplication.class, args);
		log.info("spring-boot-webservice-server-chapter33启动!");
	}
}
