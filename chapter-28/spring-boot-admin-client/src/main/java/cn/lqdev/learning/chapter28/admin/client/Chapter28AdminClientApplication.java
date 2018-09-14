package cn.lqdev.learning.chapter28.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Chapter28AdminClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Chapter28AdminClientApplication.class, args);
		log.info("Chapter28AdminClient启动!");
	}

}
