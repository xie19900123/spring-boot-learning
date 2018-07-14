package cn.lqdev.learning.springboot.chapter3.entity;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
//@EnableConfigurationProperties(value= {Config.class})
@ConfigurationProperties(prefix="config")
@Data
public class Config {
	
	String code;
	
	String name;
	
	List<String> hobby;

}
