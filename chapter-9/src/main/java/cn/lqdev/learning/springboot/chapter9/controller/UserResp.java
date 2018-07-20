package cn.lqdev.learning.springboot.chapter9.controller;

import cn.lqdev.learning.springboot.chapter9.biz.entity.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
	String id;
	
	String code;
	
	String name;
	
	StatusEnum status;

}
