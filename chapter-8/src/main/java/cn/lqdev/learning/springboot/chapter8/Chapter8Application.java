package cn.lqdev.learning.springboot.chapter8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常、数据校验处理
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class Chapter8Application {
	public static void main(String[] args) {
		SpringApplication.run(Chapter8Application.class, args);
		log.info("chapter8 服务启动");
	}
}
