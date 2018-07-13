package cn.lqdev.learning.springboot.chapter1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个springboot应用 
 * @author oKong
 *
 */


//@RestController = @Controller + @ResponseBody 默认直接返回json
@RestController
public class DemoController {

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String demo() {
		return "hello,SpringBoot!";
	}
}
