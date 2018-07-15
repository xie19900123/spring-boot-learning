package cn.lqdev.learning.springboot.chapter5.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Value("${spring.profiles.active}")
	String profile;
	
	@RequestMapping("/")
	public String demo() {
		return "profile:" + profile;
	}
}
