package cn.lqdev.learning.springboot.chapter19.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;

public class WebSocketUtil {

	/**
	 * 简单使用map进行存储在线的session
	 * 
	 */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();
    
    public static void addSession(String userNick,Session session) {
    	//putIfAbsent 添加键—值对的时候，先判断该键值对是否已经存在
    	//不存在：新增，并返回null
    	//存在：不覆盖，直接返回已存在的值
//    	ONLINE_SESSION.putIfAbsent(userNick, session);
    	//简单示例 不考虑复杂情况。。怎么简单怎么来了。。
    	ONLINE_SESSION.put(userNick, session);
    }
    
    public static void remoteSession(String userNick) {
    	ONLINE_SESSION.remove(userNick);
    }
    
    /**
     * 向某个用户发送消息
     * @param session 某一用户的session对象
     * @param message
     */
    public static void sendMessage(Session session, String message) {
    	if(session == null) {
    		return;
    	}
    	// getAsyncRemote()和getBasicRemote()异步与同步
    	Async async = session.getAsyncRemote();
    	//发送消息
    	async.sendText(message);
    }
    
    /**
     * 向所有在线人发送消息
     * @param message
     */
    public static void sendMessageForAll(String message) {
    	//jdk8 新方法
    	ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
