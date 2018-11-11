package cn.lqdev.learning.springboot.cxf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

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
