package cn.lqdev.learning.springboot.chapter13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编写mock测试服务
 * @author oKong
 *
 */
@RestController
public class DemoController {

	@GetMapping("/mock")
	public String demo(String msg) {
		return msg;
	}
}
