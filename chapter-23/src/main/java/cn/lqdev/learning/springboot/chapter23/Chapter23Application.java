package cn.lqdev.learning.springboot.chapter23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter23Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter23Application.class, args);
		log.info("Chapter23启动!");
	}
}
