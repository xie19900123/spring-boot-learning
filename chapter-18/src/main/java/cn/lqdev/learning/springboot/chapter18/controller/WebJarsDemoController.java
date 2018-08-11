package cn.lqdev.learning.springboot.chapter18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * webjar示例
 * @author oKong
 *
 */
@Controller
public class WebJarsDemoController {
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}

}
