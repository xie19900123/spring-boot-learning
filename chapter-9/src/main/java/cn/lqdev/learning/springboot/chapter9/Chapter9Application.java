package cn.lqdev.learning.springboot.chapter9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * mybatis-plus的使用
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class Chapter9Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter9Application.class, args);
		log.info("chapter9 服务启动");
	}
}
