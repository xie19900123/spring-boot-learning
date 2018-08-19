package cn.lqdev.learning.springboot.chapter22.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务示例
 * @author oKong
 *
 */
@RestController
@Slf4j
public class TaskController {
	
	@Autowired
	ScheduledTask task;

	@GetMapping("/timer")
	public String doTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				log.info("Timer定时任务执行：" + new Date());
				
			}
		}, 1000,1000);//延迟1秒启动，每1秒执行一次
		log.info("Timer定时任务启动：" + new Date());
		return "timer";
	}
	@GetMapping("/executor")
	public String ScheduledExecutorService() {
		//
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				log.info("ScheduledExecutorService定时任务一执行：" + new Date());				
			}
		}, 1, 2, TimeUnit.SECONDS);//首次延迟1秒，之后每1秒执行一次
		log.info("ScheduledExecutorService定时任务启动：" + new Date());	
		
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				log.info("ScheduledExecutorService定时任务二执行：" + new Date());			
				
			}
		}, 0, 1, TimeUnit.SECONDS);
		
		return "ScheduledExecutorService!";		
	}
	
	@Autowired
	TaskScheduler taskScheduler;
	
	@GetMapping("/poolTask")
	public String threadPoolTaskScheduler() {
	
		taskScheduler.schedule(new Runnable() {
			
			@Override
			public void run() {
				log.info("ThreadPoolTaskScheduler定时任务：" + new Date());
			}
		}, new CronTrigger("0/3 * * * * ?"));//每3秒执行一次
		return "ThreadPoolTaskScheduler!";
	}
}
