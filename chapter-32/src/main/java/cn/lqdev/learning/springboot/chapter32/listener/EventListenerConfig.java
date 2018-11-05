package cn.lqdev.learning.springboot.chapter32.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import cn.lqdev.learning.springboot.chapter32.event.CustomEvent;
import cn.lqdev.learning.springboot.chapter32.event.MessageEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听配置类
 * 
 * @author oKong
 *
 */
@Configuration
@Slf4j
public class EventListenerConfig {

	@EventListener
	public void handleEvent(Object event) {
		//监听所有事件 可以看看 系统各类时间 发布了哪些事件
		//可根据 instanceof 监听想要监听的事件
//		if(event instanceof CustomEvent) {
//			
//		}
		log.info("事件：{}", event);
	}
	
	@EventListener
	public void handleCustomEvent(CustomEvent customEvent) {
		//监听 CustomEvent事件
		log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
	}
	
	/**
	 * 监听 code为oKong的事件
	 */
	@Async
	@EventListener(condition="#customEvent.messageEntity.code == 'oKong'")
	public void handleCustomEventByCondition(CustomEvent customEvent) {
		//监听 CustomEvent事件
		log.info("监听到code为'oKong'的CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
	}
	
	@EventListener 
	public void handleObjectEvent(MessageEntity messageEntity) {
		//这个和eventbus post方法一样了
		log.info("监听到对象事件，消息为：{}", messageEntity);
		
	}
}
