package cn.lqdev.learning.springboot.cxf.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

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
