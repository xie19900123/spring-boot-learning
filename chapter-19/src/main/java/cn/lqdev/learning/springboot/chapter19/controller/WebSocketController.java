package cn.lqdev.learning.springboot.chapter19.controller;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * websocket 简易聊天
 * @author oKong
 *
 */
//由于是websocket 所以原本是@RestController的http形式 
//直接替换成@ServerEndpoint即可，作用是一样的 就是指定一个地址
//表示定义一个websocket的Server端
@Component
@ServerEndpoint(value = "/my-chat/{usernick}")
@Slf4j
public class WebSocketController {
	
	/**
	 * 连接事件 加入注解
	 * @param session
	 */
	@OnOpen
	public void onOpen(@PathParam(value = "usernick") String userNick,Session session) {
		String message = "有新游客[" + userNick + "]加入聊天室!";
		log.info(message);
		WebSocketUtil.addSession(userNick, session);	
		//此时可向所有的在线通知 某某某登录了聊天室			
		WebSocketUtil.sendMessageForAll(message);
	}
	
	@OnClose
	public void onClose(@PathParam(value = "usernick") String userNick,Session session) {
		String message = "游客[" + userNick + "]退出聊天室!";
		log.info(message);
		WebSocketUtil.remoteSession(userNick);	
		//此时可向所有的在线通知 某某某登录了聊天室			
		WebSocketUtil.sendMessageForAll(message);
	}
	
	@OnMessage
	public void OnMessage(@PathParam(value = "usernick") String userNick, String message) {
		//类似群发
		String info = "游客[" + userNick + "]：" + message;
		log.info(info);
		WebSocketUtil.sendMessageForAll(info);
	} 
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		log.error("异常:", throwable);
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throwable.printStackTrace();
	}

}
