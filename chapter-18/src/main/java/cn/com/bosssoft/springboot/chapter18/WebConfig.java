package cn.com.bosssoft.springboot.chapter18;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//配置映射关系
		//即：/webjars/** 映射到 classpath:/META-INF/resources/webjars/ 
		registry.addResourceHandler("/webjars/**")
		        .addResourceLocations("classpath:/META-INF/resources/webjars/")
				//新增 resourceChain 配置即开启缓存配置。
		        //不知道为何不加这个配置 设置了 webjars-locator 未生效。。
		        .resourceChain(true);//生产时建议开启缓存（只是缓存了资源路径而不是资源内容）,开发是可以设置为false
	}
}
