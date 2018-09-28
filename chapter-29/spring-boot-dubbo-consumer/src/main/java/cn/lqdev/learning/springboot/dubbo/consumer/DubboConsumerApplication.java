package cn.lqdev.learning.springboot.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * dubbo-消费者实例
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class DubboConsumerApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DubboConsumerApplication.class, args);
		log.info("spring-boot-dubbo-consumer启动!");
	}
}
