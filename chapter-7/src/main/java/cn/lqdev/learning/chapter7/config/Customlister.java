package cn.lqdev.learning.chapter7.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.slf4j.Slf4j;

@ServletComponentScan
@Slf4j
public class Customlister implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		log.info("监听器：销毁");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		log.info("监听器：初始化");
	}

}
