package cn.lqdev.learning.springboot.chapter31.biz.entity;

import java.io.Serializable;
import java.util.Date;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知消息对象
 * @author oKong
 *
 */
@Document(collection="notify_msg")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8985545025018238754L;

	@Id
	String id;
	
	/**
	 * 消息类型
	 */
	@Indexed
	String notifyType;
	
	/**
	 * 消息单号
	 */
	@Indexed
	String notifyNo;
	
	/**
	 * 消息通知日期
	 */
	String notifyDate;
	
	/**
	 * 消息体
	 */
	@Field("notifyMsg")//可指定存储时的字段名
	String notifyMsg;
	
	/**
	 * 创建时间
	 */
	@CreatedDate
	Date gmtCreate;
}
