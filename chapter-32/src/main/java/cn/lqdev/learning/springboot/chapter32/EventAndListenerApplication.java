package cn.lqdev.learning.springboot.chapter32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.lqdev.learning.springboot.chapter32.listener.MyApplicationStartingEventListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件监听
 * 
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class EventAndListenerApplication {
	public static void main(String[] args) throws Exception {

        SpringApplication app =new SpringApplication(EventAndListenerApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());//加入自定义的监听类
        app.run(args);
		log.info("spring-boot-event-listener-chapter32启动!");
	}
}
