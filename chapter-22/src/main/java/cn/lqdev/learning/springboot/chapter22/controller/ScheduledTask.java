package cn.lqdev.learning.springboot.chapter22.controller;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTask {

	/**
	 * 自动扫描，启动时间点之后5秒执行一次
	 */
	//@Async("scheduledPoolTaskExecutor")
	//@Scheduled(fixedRate=5000)
	public void getCurrentDate() {
		log.info("Scheduled定时任务执行：" + new Date());
	}
}
