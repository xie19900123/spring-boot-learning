package cn.lqdev.learning.springboot.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ChapterApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		log.info("jar,chapter开始启动!");
		SpringApplication.run(ChapterApplication.class, args);
	//	new SpringApplicationBuilder().sources(ChapterApplication.class).web(false).run(args);
    //之后这里设置业务逻辑 比如挂起一个线程 或者设置一个定时任务。保证不退出
		//不然它就是一个启动类，启动后就停止了。
		log.info("jar,chapter启动完成!");
	} 
	 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		log.info("外部tomcat,chapter启动!");
		return application.sources(ChapterApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("CommandLineRunner运行,info输出");
//		loggingSystem.setLogLevel(null, LogLevel.DEBUG);
		log.debug("CommandLineRunner运行:debug输出");
	}
	
	@Autowired
	LoggingSystem loggingSystem;
}
