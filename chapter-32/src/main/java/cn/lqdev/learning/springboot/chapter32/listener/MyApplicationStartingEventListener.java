package cn.lqdev.learning.springboot.chapter32.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 示例-启动事件
 * @author oKong
 *
 */
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent>{

	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		// TODO Auto-generated method stub
		//由于 log相关还未加载 使用了也输出不了的
//		log.info("ApplicationStartingEvent事件发布:{}", event);
		System.out.println("ApplicationStartingEvent事件发布:" + event.getTimestamp());
	}

}
