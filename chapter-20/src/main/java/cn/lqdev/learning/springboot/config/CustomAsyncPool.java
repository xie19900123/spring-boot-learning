package cn.lqdev.learning.springboot.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomAsyncPool extends WebMvcConfigurerAdapter{

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
		taskExecutor.initialize();
		return taskExecutor;
	}
	
	@Override
	public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
		//处理 callable超时
		configurer.setDefaultTimeout(60*1000);
		configurer.registerCallableInterceptors(timeoutInterceptor());
		configurer.setTaskExecutor(getAsyncThreadPoolTaskExecutor());
	}
	
	@Bean
	public TimeoutCallableProcessor timeoutInterceptor() {
		return new TimeoutCallableProcessor();
	}
	
}
