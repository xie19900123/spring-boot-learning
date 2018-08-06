package cn.lqdev.learning.springboot.chapter16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter16Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter16Application.class, args);
		log.info("Chapter16 启动!");
	}
}
