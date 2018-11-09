package cn.lqdev.learning.springboot.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import cn.lqdev.learning.springboot.ws.client.WsAuthorClient;

/**
 * 客户端调用配置
 * @author oKong
 *
 */
@Configuration
public class WsClientConfig {
	
	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //会扫描此类下面的对应的 jaxb2实体类 因为是使用 Marshaller和 unmarshaller来进行xml和bean直接转换的
        //具体是判断此路径下是否包含 ObjectFactory.class 文件
        //设置 JAXBContext 对象
        marshaller.setContextPath("cn.lqdev.webservice");
        //或者使用 以下方式设置
//        marshaller.setPackagesToScan(packagesToScan);
//        marshaller.setClassesToBeBound(classesToBeBound);
        return marshaller;
    }
	
	/*
	 * 创建bean
	 */
    @Bean
    public WsAuthorClient wsClient(Jaxb2Marshaller marshaller) {
    	WsAuthorClient client = new WsAuthorClient();
    	//默认对应的ws服务地址 client请求中还能动态修改的
        client.setDefaultUri("http://127.0.0.1:8090/ws");
        client.setMarshaller(marshaller);//指定转换类
        client.setUnmarshaller(marshaller);
        return client;
    }
}
