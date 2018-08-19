package cn.lqdev.learning.springboot.chapter22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot | 第二十二章：定时任务
 * 
 * @author <a href="https://github.com/xie19900123">oKong</>
 *
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class Chapter22Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter22Application.class, args);
		log.info("Chapter22启动!");
	}
}
