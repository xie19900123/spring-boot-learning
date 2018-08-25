package cn.lqdev.learning.springboot.chapter25.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单输出
 * @author oKong
 *
 */
@RestController
@Slf4j
public class DemoController {

	@GetMapping("/")
	public String index() {
		log.info("success!");
		return "success";
	}
}
