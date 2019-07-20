package cn.lqdev.learning.springboot.chapter38.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lqdev.learning.springboot.chapter38.constant.ApplicationConstant;

/** 
*
* @ClassName   类名：RabbitConfig 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年7月17日
* @author      创建人：oKong
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年7月17日   oKong   创建该类功能。
*
***********************************************************************
*</p>
*/
@Configuration
public class RabbitConfig {
	
	@Autowired
	ConnectionFactory connectionFactory;
	
	/**
	 * 消费者线程数 设置大点 大概率是能通知到的
	 */
	@Value("${http.notify.concurrency:50}")
	int concurrency;
	
	/**
	 * 延迟队列的消费者线程数 可设置小点
	 */
	@Value("${http.notify.delay.concurrency:20}")
	int delayConcurrency;
	
	@Bean
	public RabbitAdmin rabbitAdmin() {
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	public DirectExchange httpMessageNotifyDirectExchange(RabbitAdmin rabbitAdmin) {
		//durable 是否持久化
		//autoDelete 是否自动删除，即服务端或者客服端下线后 交换机自动删除
		DirectExchange directExchange = new DirectExchange(ApplicationConstant.HTTP_MESSAGE_EXCHANGE,true,false);
		directExchange.setAdminsThatShouldDeclare(rabbitAdmin);
		return directExchange;
	}
	
	//设置消息队列
	@Bean
	public Queue httpMessageStartQueue(RabbitAdmin rabbitAdmin) {
		 
       /*
                       创建接收队列，4个参数
		 name - 队列名称
		 durable - false，不进行持有化
		 exclusive - true，独占性
		 autoDelete - true，自动删除*/
		Queue queue = new Queue(ApplicationConstant.HTTP_MESSAGE_START_QUEUE_NAME, true, false, false);
		queue.setAdminsThatShouldDeclare(rabbitAdmin);
		return queue;
	}
	
    //队列绑定交换机
	@Bean
	public Binding bindingStartQuene(RabbitAdmin rabbitAdmin,DirectExchange httpMessageNotifyDirectExchange, Queue httpMessageStartQueue) {
		Binding binding = BindingBuilder.bind(httpMessageStartQueue).to(httpMessageNotifyDirectExchange).with(ApplicationConstant.HTTP_MESSAGE_START_RK);
		binding.setAdminsThatShouldDeclare(rabbitAdmin);
		return binding;
	}
	
	@Bean
	public Queue httpMessageOneQueue(RabbitAdmin rabbitAdmin) {
		Queue queue = new Queue(ApplicationConstant.HTTP_MESSAGE_ONE_QUEUE_NAME, true, false, false);
		queue.setAdminsThatShouldDeclare(rabbitAdmin);
		return queue;
	}
	
	@Bean
	public Binding bindingOneQuene(RabbitAdmin rabbitAdmin,DirectExchange httpMessageNotifyDirectExchange, Queue httpMessageOneQueue) {
		Binding binding = BindingBuilder.bind(httpMessageOneQueue).to(httpMessageNotifyDirectExchange).with(ApplicationConstant.HTTP_MESSAGE_ONE_RK);
		binding.setAdminsThatShouldDeclare(rabbitAdmin);
		return binding;
	}
	
	//-------------设置延迟队列--开始--------------------
	@Bean
	public Queue httpDelayOneQueue() {
		//name - 队列名称
		//durable - true
		//exclusive - false
		//autoDelete - false
		return QueueBuilder.durable("http.message.dlx.one")
				//以下是重点：当变成死信队列时，会转发至 路由为x-dead-letter-exchange及x-dead-letter-routing-key的队列中
				.withArgument("x-dead-letter-exchange", ApplicationConstant.HTTP_MESSAGE_EXCHANGE)
				.withArgument("x-dead-letter-routing-key", ApplicationConstant.HTTP_MESSAGE_ONE_RK)
				.withArgument("x-message-ttl", 1*60*1000)//1分钟 过期时间（单位：毫秒），当过期后 会变成死信队列，之后进行转发
				.build();
	}
	//绑定到交换机上
	@Bean
	public Binding bindingDelayOneQuene(RabbitAdmin rabbitAdmin, DirectExchange httpMessageNotifyDirectExchange, Queue httpDelayOneQueue) {
		Binding binding = BindingBuilder.bind(httpDelayOneQueue).to(httpMessageNotifyDirectExchange).with("delay.one");
		binding.setAdminsThatShouldDeclare(rabbitAdmin);
		return binding;
	}
	//-------------设置延迟队列--结束--------------------

	//建议将正常的队列和延迟处理的队列分开
	//设置监听容器
	@Bean("notifyListenerContainer")
	public SimpleRabbitListenerContainerFactory httpNotifyListenerContainer() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);// 手动ack
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(1);
		factory.setConcurrentConsumers(concurrency);
		return factory;
	}

	// 设置监听容器
	@Bean("delayNotifyListenerContainer")
	public SimpleRabbitListenerContainerFactory httpDelayNotifyListenerContainer() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);// 手动ack
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(1);
		factory.setConcurrentConsumers(delayConcurrency);
		return factory;
	}
}
