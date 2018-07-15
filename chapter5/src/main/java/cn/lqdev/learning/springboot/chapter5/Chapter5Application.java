package cn.lqdev.learning.springboot.chapter5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 多环境配置
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class Chapter5Application {
	public static void main(String[] args) {
		SpringApplication.run(Chapter5Application.class, args);
		log.info("多环境应用启动.");
	}

}
