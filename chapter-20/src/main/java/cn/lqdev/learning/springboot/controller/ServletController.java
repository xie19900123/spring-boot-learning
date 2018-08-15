package cn.lqdev.learning.springboot.controller;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用servlet方式进行异步请求
 * @author oKong
 *
 */
@Slf4j
@RestController
public class ServletController {
	
	@RequestMapping("/servlet/orig")
	public void todo(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		//这里来个休眠
		Thread.sleep(100);
		response.getWriter().println("这是【正常】的请求返回");
	}
	
	@RequestMapping("/servlet/async")
	public void todoAsync(HttpServletRequest request,
            HttpServletResponse response) {
		AsyncContext asyncContext = request.startAsync();
		asyncContext.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				log.info("超时了：");
				//做一些超时后的相关操作
				
			}
			
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
				log.info("线程开始");
			}
			
			@Override
			public void onError(AsyncEvent event) throws IOException {
				log.info("发生错误：",event.getThrowable());
				
			}
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				log.info("执行完成");
				//这里可以做一些清理资源的操作
				
			}
		});
		//设置超时时间
		asyncContext.setTimeout(200);
		//也可以不使用start 进行异步调用
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				编写业务逻辑
//				
//			}
//		}).start();
		
		asyncContext.start(new Runnable() {			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					log.info("内部线程：" + Thread.currentThread().getName());
					asyncContext.getResponse().setCharacterEncoding("utf-8");
					asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
					asyncContext.getResponse().getWriter().println("这是【异步】的请求返回");
				} catch (Exception e) {
					log.error("异常：",e);
				}
				//异步请求完成通知
				//此时整个请求才完成
				//其实可以利用此特性 进行多条消息的推送 把连接挂起。。
				asyncContext.complete();
			}
		});
		//此时之类 request的线程连接已经释放了
		log.info("线程：" + Thread.currentThread().getName());
	}

}
