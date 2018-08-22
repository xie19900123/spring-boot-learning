package cn.lqdev.learning.springboot.chapter23.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志管理之整合篇
 * @author oKong
 *
 */
@RestController
@Slf4j
public class LoggingController {

	@RequestMapping("/log")
	public String index() {
		log.trace("A TRACE Message");
		log.debug("A DEBUG Message");
		log.info("An INFO Message");
		log.warn("A WARN Message");
		log.error("An ERROR Message");
		return "logging!!";

	}
}
