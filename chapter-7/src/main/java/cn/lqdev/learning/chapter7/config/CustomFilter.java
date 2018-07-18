package cn.lqdev.learning.chapter7.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *  自定义过滤器
 * 
 * @author oKong
 *
 */
//注册器名称为customFilter,拦截的url为所有
//@WebFilter(filterName="customFilter",urlPatterns={"/*"})
@Slf4j
public class CustomFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter 初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("doFilter 请求处理");
		//对request、response进行一些预处理
		// 比如设置请求编码
		// request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
		//TODO 进行业务逻辑
		
		//链路 直接传给下一个过滤器
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info("filter 销毁");
	}
}
