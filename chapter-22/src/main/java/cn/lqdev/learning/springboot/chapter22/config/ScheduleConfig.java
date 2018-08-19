package cn.lqdev.learning.springboot.chapter22.config;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(taskExecutor());
		taskRegistrar.getScheduler().schedule(new Runnable() {
			
			@Override
			public void run() {
				log.info("SchedulingConfigurer定时任务：" + new Date());
			}
		}, new CronTrigger("0/3 * * * * ?"));//每3秒执行一次
		
	}
	
	@Bean("taskExecutor")
	public TaskScheduler taskExecutor() {
	    ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
	    executor.setPoolSize(20);
	    executor.setThreadNamePrefix("oKong-Executor-");
	    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	    //调度器shutdown被调用时等待当前被调度的任务完成
	    executor.setWaitForTasksToCompleteOnShutdown(true);
	    //等待时长
	    executor.setAwaitTerminationSeconds(60);
	    return executor;
	}

}
