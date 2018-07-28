package cn.lqdev.learning.springboot.chapter14;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于Postman的RESTfulAPI接口测试
 * @author oKong
 *
 */
@RestController
@Slf4j
public class RestfulController {

	@GetMapping("/get")
	public String get(String msg) {
		log.info("get方式!");
		return msg;
	}
	
	@PostMapping("/post")
	public String post(@RequestBody String msg) {
		log.info("post方式!");
		return msg;
	}
	
	@PutMapping("/put")
	public String put(@RequestBody String msg) {
		log.info("put方式!");
		return msg;
	}
	
	@DeleteMapping("/delete")
	public String delete(String msg) {
		log.info("delete方式!");
		return "delete " + msg + " success!";
	}
	
	/**
	 * 设置返回状态为417
	 * @param msg
	 * @return
	 */
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public String status(String msg) {
		log.info("status测试!");
		return msg;
	}
}
