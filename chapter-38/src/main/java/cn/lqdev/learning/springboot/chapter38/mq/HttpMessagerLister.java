package cn.lqdev.learning.springboot.chapter38.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import cn.hutool.json.JSONUtil;
import cn.lqdev.learning.springboot.chapter38.constant.ApplicationConstant;
import cn.lqdev.learning.springboot.chapter38.entity.HttpEntity;
import cn.lqdev.learning.springboot.chapter38.service.HttpMessagerService;
import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：HttpMessagerLister 
* @Description 功能说明：http通知消费监听接口
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
@Component
@Slf4j
public class HttpMessagerLister {
	
	@Autowired
	HttpMessagerService messagerService;
	
	@RabbitListener(id = "httpMessageNotifyConsumer", queues = {ApplicationConstant.HTTP_MESSAGE_START_QUEUE_NAME}, containerFactory = "notifyListenerContainer")
	public void httpMessageNotifyConsumer(Message message, Channel channel) throws Exception {
		doHandler(message, channel);
	}
	
	@RabbitListener(id= "httpDelayMessageNotifyConsumer", queues = {
			ApplicationConstant.HTTP_MESSAGE_ONE_QUEUE_NAME,}, containerFactory = "delayNotifyListenerContainer")
	public void httpDelayMessageNotifyConsumer(Message message, Channel channel) throws Exception {
		doHandler(message, channel);
	}
	
	private void doHandler(Message message, Channel channel) throws Exception {
		String body = new String(message.getBody(),"utf-8");
		String queue = message.getMessageProperties().getConsumerQueue();
		log.info("接收到通知请求：{}，队列名：{}",body, queue);
		//消息对象转换
		try {
			HttpEntity httpNotifyDto = JSONUtil.toBean(body, HttpEntity.class);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			//发送通知
			messagerService.notify(queue, httpNotifyDto);
		} catch(Exception e) {
			log.error(e.getMessage());
			//ack
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}
	}

}
