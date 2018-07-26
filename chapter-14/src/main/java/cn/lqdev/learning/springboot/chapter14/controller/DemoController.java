package cn.lqdev.learning.springboot.chapter14.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/")
	public String demo() {
		return "hello,docker!";
	}
}
