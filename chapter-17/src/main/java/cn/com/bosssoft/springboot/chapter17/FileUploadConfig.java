package cn.com.bosssoft.springboot.chapter17;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileUploadConfig {

	
	@Bean
	public MultipartResolver custom() {
		return new CommonsMultipartResolver();
	}
}
