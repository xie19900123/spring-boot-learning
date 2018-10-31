package cn.lqdev.learning.chapter30;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

/**
 * jpa集成
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class JpaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JpaApplication.class, args);
		log.info("spring-boot-jpa-chapter30启动!");
	}

}
