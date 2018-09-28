package cn.lqdev.learning.springboot.dubbo.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * dubbo-提供者
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class DubboProviderApplication {

	public static void main(String[] args) throws Exception {
		//由于dubbo提供者只是单纯提供服务的 可以为一个非web环境
		new SpringApplicationBuilder(DubboProviderApplication.class).web(false).run(args);
		log.info("spring-boot-dubbo-provider启动!");
	}

}
