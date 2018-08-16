package cn.lqdev.learning.springboot.chapter21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class Chapter21Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter21Application.class, args);
		log.info("Chapter21启动!");
	}
}
