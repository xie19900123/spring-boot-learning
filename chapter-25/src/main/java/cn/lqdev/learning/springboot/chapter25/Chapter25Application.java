package cn.lqdev.learning.springboot.chapter25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter25Application {

	public static void main(String[] args) {        
		SpringApplication.run(Chapter25Application.class, args);		
		log.info("Chapter25启动!");
	}
}
