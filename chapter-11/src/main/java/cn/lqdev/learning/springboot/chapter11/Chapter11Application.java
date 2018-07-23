package cn.lqdev.learning.springboot.chapter11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter11Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter11Application.class, args);
		log.info("Chapter11 服务启动!");
	}
}
