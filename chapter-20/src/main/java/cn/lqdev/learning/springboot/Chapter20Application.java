package cn.lqdev.learning.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter20Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter20Application.class, args);
		log.info("Chapter20 启动!");
	}
}
