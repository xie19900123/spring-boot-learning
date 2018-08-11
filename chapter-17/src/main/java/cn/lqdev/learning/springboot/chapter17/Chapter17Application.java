package cn.lqdev.learning.springboot.chapter17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
//这里需要注意 因为springboot自动配置原因，MultipartAutoConfiguration会自动加载，而加载后就是默认的方式了:StandardServletMultipartResolver
//所以这里不自动加载此配置，让CommonsMultipartResolver来接管
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class}) 
@Slf4j
public class Chapter17Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter17Application.class, args);
		log.info("Chapter17 启动!");
	}
		
}
