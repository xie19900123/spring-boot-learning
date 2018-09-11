package cn.lqdev.learning.springboot.chapter27;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter27Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Chapter27Application.class, args);
		log.info("chapter27启动!");
	}

}
