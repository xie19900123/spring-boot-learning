package cn.lqdev.learning.springboot.chapter8.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter8.controller.req.DemoReq;
import cn.lqdev.learning.springboot.chapter8.exception.CommonException;
import cn.lqdev.learning.springboot.chapter8.util.ValidatorUtil;

@RestController
public class DemoController {

	@GetMapping("/demo")
	public String demo() {
		throw new CommonException("01001", "发送异常");
	}
	
	@GetMapping("/demo/valid")
	public String demoValid(@Valid DemoReq req) {
		//手动校验
		ValidatorUtil.validate(req);
		return req.getCode() + "," + req.getName();
	}
}
