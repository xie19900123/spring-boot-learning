package cn.lqdev.learning.springboot.cxf.client.config;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lqdev.webservice.AuthorPortType;
import cn.lqdev.webservice.AuthorService;

/**
 * 配置类
 * 
 * @author oKong
 *
 */
@Configuration
public class CxfClientConfig {

	
	/**
	 *  以接口代理方式进行调用 AuthorPortType接口
	 */
	@Bean("cxfProxy")
	public AuthorPortType createAuthorPortTypeProxy() {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(AuthorPortType.class);
		jaxWsProxyFactoryBean.setAddress(WsConst.SERVICE_ADDRESS);//服务地址：http://127.0.0.1:8080/ws/autho
		jaxWsProxyFactoryBean.getOutInterceptors().add(createInterceptor());//加入自定义拦截器
		return (AuthorPortType) jaxWsProxyFactoryBean.create();
	}
	
	/*
	 * 采用动态工厂方式 不需要指定服务接口
	 */
	@Bean
	public Client createDynamicClient() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(WsConst.SERVICE_ADDRESS);
		client.getOutInterceptors().add(createInterceptor());
		return client;
	}
	
	@Bean
	public Interceptor<SoapMessage> createInterceptor() {
		return new AuthInterceptor();
	}
	
	/*
	 * 直接调用
	 */
	@Bean("jdkProxy")
	public AuthorPortType createJdkService() {
		AuthorService authorService = new AuthorService();
		return authorService.getAuthorPortName();
	}
}
