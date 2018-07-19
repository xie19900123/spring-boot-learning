package cn.lqdev.learning.chapter7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author oKong
 *
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{
	
	@Override
	 public void addInterceptors(InterceptorRegistry registry) {
		 //注册拦截器 拦截规则
		//多个拦截器时 以此添加 执行顺序按添加顺序
		registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/*");
	 }
	
	@Bean
	public static HandlerInterceptor getHandlerInterceptor() {
		return new CustomHandlerInterceptor();
	}
}
