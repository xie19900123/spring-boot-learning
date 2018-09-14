package cn.lqdev.learning.chapter30.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAdminServer
@Slf4j
public class Chapter28AdminServerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Chapter28AdminServerApplication.class, args);
		log.info("Chapter28AdminServer启动!");
	}
}
