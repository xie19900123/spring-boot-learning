package cn.lqdev.learning.springboot.chapter38.controller;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter38.constant.ApplicationConstant;
import cn.lqdev.learning.springboot.chapter38.entity.HttpEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：HttpDemoController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年7月17日
* @author      创建人：xds
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年7月17日   xds   创建该类功能。
*
***********************************************************************
*</p>
*/
@Slf4j
@RestController
@Api(tags = "http测试接口")
public class HttpDemoController {

	@Autowired
	AmqpTemplate mqTemplate;
	
	@Autowired
	RabbitListenerEndpointRegistry registry;
	
	@PostMapping("/send")
	@ApiOperation(value="send",notes = "发送http测试")
	public String sendHttp(@RequestBody HttpEntity httpEntity) {
		//发送http请求
		log.info("开始发起http请求，发布异步消息：{}", httpEntity);
		mqTemplate.convertAndSend(ApplicationConstant.HTTP_MESSAGE_EXCHANGE, ApplicationConstant.HTTP_MESSAGE_START_RK, cn.hutool.json.JSONUtil.toJsonStr(httpEntity));
		return "发送成功：url=" + httpEntity.getUrl();		
	}
	
	@GetMapping("/set")
	@ApiOperation(value = "set", notes = "设置消息监听器的状态")
	public String setSimpleMessageListenerContainer(String status) {
		if("1".equals(status)) {
			registry.getListenerContainer("httpDelayMessageNotifyConsumer").start();
		} else {
			registry.getListenerContainer("httpDelayMessageNotifyConsumer").stop();
		}
		return status;
	}
}
