package cn.lqdev.learning.springboot.chapter24.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter24.config.Log;

/**
 * aop统一异常示例
 * @author oKong
 *
 */
@RestController
public class DemoController {
	/**
	 * 简单方法示例
	 * @param hello
	 * @return
	 */
	@RequestMapping("/aop")
	@Log(value="请求了aopDemo方法")
	public String aopDemo(String hello) {
		return "请求参数为：" + hello;
	}

	/**
	 * 不拦截日志示例
	 * @param hello
	 * @return
	 */
	@RequestMapping("/notaop")
	@Log(ignore=true)
	public String notAopDemo(String hello) {
		return "此方法不记录日志，请求参数为：" + hello;
	}
}
