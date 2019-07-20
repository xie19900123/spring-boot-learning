package cn.lqdev.learning.springboot.chapter38.constant;

import java.util.HashMap;
import java.util.Map;

/** 
*
* @ClassName   类名：ApplicationConstant 
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
public class ApplicationConstant {
	
	/**
	 * 发送http通知的 exchange 队列
	 */
	public static final String HTTP_MESSAGE_EXCHANGE = "http.message.exchange";
	
	/**
	 * 配置消息队列和路由key值
	 */
	public static final String HTTP_MESSAGE_START_QUEUE_NAME = "http.message.start";
	public static final String HTTP_MESSAGE_START_RK = "rk.start";
	
	public static final String HTTP_MESSAGE_ONE_QUEUE_NAME = "http.message.one";
	public static final String HTTP_MESSAGE_ONE_RK = "rk.one";
	
	/**
	 * 通知队列对应的延迟队列关系，即过期队列之后发送到下一个的队列信息,可以根据实际情况添加，当然也可以根据一定规则自动生成
	 */
	public static final Map<String,String> delayRefMap = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -779823216035682493L;

		{
			put(HTTP_MESSAGE_START_QUEUE_NAME, "delay.one");
		}
	};
	

}
