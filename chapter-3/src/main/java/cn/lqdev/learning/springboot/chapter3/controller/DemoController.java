package cn.lqdev.learning.springboot.chapter3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter3.entity.Config;

/**
 *  属性获取
 * @author oKong
 *
 */
@RestController
public class DemoController {

	@Value("${blog.address}")
	String address;
	
	@Value("${blog.author}")
	String author;
	
	@Value("${blog.desc}")	
	String desc;
	
	@Autowired
	Config config;
	
	@RequestMapping("/")
	public Map<String,Object> demo() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("desc", desc);
		map.put("config", config);
		return map;
	}
}
