package cn.lqdev.learning.springboot.cxf.config;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;

/**
 * 简易-安全校验拦截器
 * 
 * @author oKong
 *
 */
@Slf4j
public class CheckAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public CheckAuthInterceptor() {
		super(Phase.PRE_INVOKE);// 拦截节点：调用之前
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		log.info("检验拦截器开始检验：{}", message);
		// 处理方法
		List<Header> headers = message.getHeaders();

		// 判断是否存header
		// 检查headers是否存在
		if (headers == null | headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("验证失败，请传入正确参数(40001)"));//可自定义编码规范
		}
		//取出header
		Header header = headers.get(0);
		//获取对象
		Element element = (Element) header.getObject();//这里获取的就时 auth对象了
		NodeList tokenNode = element.getElementsByTagName("token");
		if(tokenNode == null || tokenNode.getLength() < 1) {
			//无token节点
			throw new Fault(new IllegalArgumentException("验证失败，请传入正确参数(40002)"));//自定义编码规范
		}
		//获取token
		String token = tokenNode.item(0).getTextContent();
		log.info("请求的token为：{}", token);
		//这里可以对token 有效性进行判断
	}
}
