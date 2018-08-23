package cn.lqdev.learning.springboot.chapter24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class Chapter24Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter24Application.class, args);
		log.info("Chapter24启动!");
	}
}
