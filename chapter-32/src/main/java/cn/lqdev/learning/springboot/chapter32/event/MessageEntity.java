package cn.lqdev.learning.springboot.chapter32.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息实体类
 * @author oKong
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	String message;
	
	String code;
}
