package cn.lqdev.learning.springboot.chapter12;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	/**
	 * 定义一个名为：oKong 的队列
	 * @return
	 */
	@Bean
	public Queue okongQueue() {
		return new Queue("okong");
	}

}
