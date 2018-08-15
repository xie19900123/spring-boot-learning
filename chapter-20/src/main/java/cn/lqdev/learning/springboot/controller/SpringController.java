package cn.lqdev.learning.springboot.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import lombok.extern.slf4j.Slf4j;

/**
 * spring方式实现异步请求
 * 
 * @author oKong
 *
 */
@Slf4j
@RestController
public class SpringController {

	@RequestMapping("/callable")
	public Callable<String> callable() {
		log.info("外部线程：" + Thread.currentThread().getName());
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				log.info("内部线程：" + Thread.currentThread().getName());
				return "callable!";
			}
		};
	}
	
	@RequestMapping("/deferredresult")
	public DeferredResult<String> deferredResult(){
		log.info("外部线程：" + Thread.currentThread().getName());
		//设置超时时间
		DeferredResult<String> result = new DeferredResult<String>(60*1000L);
		//处理超时事件 采用委托机制
		result.onTimeout(new Runnable() {
			
			@Override
			public void run() {
				log.error("DeferredResult超时");
				result.setResult("超时了!");
			}
		});
		result.onCompletion(new Runnable() {
			
			@Override
			public void run() {
				//完成后
				log.info("调用完成");
			}
		});
		FIXED_THREAD_POOL.execute(new Runnable() {
			
			@Override
			public void run() {
				//处理业务逻辑
				log.info("内部线程：" + Thread.currentThread().getName());
				//返回结果
				result.setResult("DeferredResult!!");
			}
		});
		return result;
	}
	
	/**
	 * 线程池
	 */
    public static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(30);
    
    
    @RequestMapping("/webAsyncTask")
    public WebAsyncTask<String> webAsyncTask() {
    	log.info("外部线程：" + Thread.currentThread().getName());
    	WebAsyncTask<String> result = new WebAsyncTask<String>(60*1000L, new Callable<String>() {

			@Override
			public String call() throws Exception {
				log.info("内部线程：" + Thread.currentThread().getName());
				return "WebAsyncTask!!!";
			}
		});
    	result.onTimeout(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "WebAsyncTask超时!!!";
			}
		});
    	result.onCompletion(new Runnable() {
			
			@Override
			public void run() {
				//超时后 也会执行此方法
				log.info("WebAsyncTask执行结束");
			}
		});
    	return result;
    }
}
