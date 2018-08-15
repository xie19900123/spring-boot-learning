package cn.lqdev.learning.springboot.config;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

import cn.lqdev.learning.springboot.exception.CustomAsyncRequestTimeoutException;

public class TimeoutCallableProcessor extends CallableProcessingInterceptorAdapter{
	@Override
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
		HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
		//记录超时的url地址
		return new CustomAsyncRequestTimeoutException(httpRequest.getRequestURI());
	}
}
