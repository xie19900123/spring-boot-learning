package cn.lqdev.learning.springboot.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * ws-客户端调用示例
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class WebServiceClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebServiceClientApplication.class, args);
		log.info("spring-boot-webservice-client-chapter33启动!");
	}

}
