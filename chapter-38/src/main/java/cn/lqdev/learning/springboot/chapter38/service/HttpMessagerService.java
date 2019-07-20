package cn.lqdev.learning.springboot.chapter38.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.http.HttpUtil;
import cn.lqdev.learning.springboot.chapter38.constant.ApplicationConstant;
import cn.lqdev.learning.springboot.chapter38.entity.HttpEntity;
import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：HttpMessagerService 
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
@Component
@Slf4j
public class HttpMessagerService {
	
	@Autowired
	AmqpTemplate mqTemplate;	
	
	public void notify(String queue,HttpEntity httpEntity) {
		//发起请求
		log.info("开始发起http请求:{}", httpEntity);
		try {
			switch(httpEntity.getMethod().toLowerCase()) {
			case "POST":
				  HttpUtil.post(httpEntity.getUrl(), httpEntity.getParams());
				  break;
			case "GET":
			default:
				HttpUtil.get(httpEntity.getUrl(), httpEntity.getParams());
			}
		} catch (Exception e) {
			//发生异常，放入延迟队列中
			String nextRk = ApplicationConstant.delayRefMap.get(queue);
			if(ApplicationConstant.HTTP_MESSAGE_ONE_QUEUE_NAME.equals(queue)) {
				//若已经是最后一个延迟队列的消息队列了，则后续可直接放入数据库中 待后续定时策略进行再次发送
				log.warn("http通知已经通知N次失败，进入定时进行发起通知,url={}", httpEntity.getUrl());
			} else {
			   log.warn("http重新发送通知：{}, 通知队列rk为：{}, 原队列：{}", httpEntity.getUrl(), nextRk, queue);
			   mqTemplate.convertAndSend(ApplicationConstant.HTTP_MESSAGE_EXCHANGE, nextRk, cn.hutool.json.JSONUtil.toJsonStr(httpEntity));
			}
		}
	}

}
