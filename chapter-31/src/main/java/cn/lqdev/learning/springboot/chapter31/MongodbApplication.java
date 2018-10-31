package cn.lqdev.learning.springboot.chapter31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.slf4j.Slf4j;

/**
 * mongodb 集成示例
 * @author oKong
 *
 */
@SpringBootApplication
@EnableMongoAuditing
//@EnableMongoRepositories(basePackages="cn.lqdev")//当有些dao不在default page下时 可通过此方法进行注册扫描包
@Slf4j
public class MongodbApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MongodbApplication.class, args);
		log.info("spring-boot-mongodb-chapter31启动!");
	}
}
