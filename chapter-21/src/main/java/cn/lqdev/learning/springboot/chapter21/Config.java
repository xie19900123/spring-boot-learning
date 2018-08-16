package cn.lqdev.learning.springboot.chapter21;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Config {

	/**
	 * 配置线程池
	 * @return
	 */
	@Bean(name = "asyncPoolTaskExecutor")
	public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setMaxPoolSize(200);
		taskExecutor.setQueueCapacity(25);
		taskExecutor.setKeepAliveSeconds(200);
		taskExecutor.setThreadNamePrefix("oKong-");
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//调度器shutdown被调用时等待当前被调度的任务完成
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		//等待时长
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.initialize();
		return taskExecutor;
	}
}

