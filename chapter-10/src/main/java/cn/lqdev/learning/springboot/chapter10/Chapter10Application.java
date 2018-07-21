package cn.lqdev.learning.springboot.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Swagger2集成及使用
 * @author xiedeshou
 *
 */
@SpringBootApplication
@Slf4j
public class Chapter10Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Chapter10Application.class, args);
		log.info("chapter1-10 服务启动");
	}

}
