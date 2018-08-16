package cn.lqdev.learning.springboot.chapter21.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter21.service.SyncService;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步调用示例
 * @author oKong
 *
 */
@RestController
@Slf4j
public class AsyncController {
	
	@Autowired
	SyncService syncService;
	
	@GetMapping("/async")
	public String doAsync() throws InterruptedException, ExecutionException, TimeoutException {
		long start = System.currentTimeMillis();
		log.info("方法执行开始：{}", start);
		//调用同步方法
		syncService.syncEvent();
		long syncTime = System.currentTimeMillis();
		log.info("同步方法用时：{}", syncTime - start);
		//调用异步方法
		Future<String> doFutrue = syncService.asyncEvent();		
//		while(true) {
//			//判断异步任务是否完成
//			if(doFutrue.isDone()) {
//				break;
//			}
//			Thread.sleep(100);
//		}
		//get方法会一直堵塞，直到等待执行完成才返回
		//get(long timeout, TimeUnit unit) 在设置时间类未返回结果，会直接排除异常TimeoutException，messages为null
		String result = doFutrue.get(10, TimeUnit.MILLISECONDS);//60毫秒
		long asyncTime = System.currentTimeMillis();
		log.info("异步方法用时：{},返回值为：{}", (asyncTime - syncTime),result);
		log.info("方法执行完成：{}!",asyncTime);
		return "async!!!";
	}
}
