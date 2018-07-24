package cn.lqdev.learning.springboot.chapter12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter12Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter12Application.class, args);
		log.info("Chapter12 服务启动!");
	}
}
