package cn.lqdev.learning.springboot.chapter10.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoReq {

	String code;
	
	String name;
	
	String remark;
}
