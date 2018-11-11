package cn.lqdev.learning.springboot.cxf.client.config;

import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 简易-安全校验拦截器
 * @author oKong
 *
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage>{
	
	public AuthInterceptor() {
		super(Phase.PREPARE_SEND);//准备请求时进行拦截
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		//处理方法
		List<Header> headers = message.getHeaders();
		
		Document doc = DOMUtils.createDocument();
		Element element = doc.createElement("auth");
		Element tokenEle = doc.createElement("token");
		tokenEle.setTextContent(UUID.randomUUID().toString());
		element.appendChild(tokenEle);
		//这里需要注意 默认情况下 是使用 org.w3c.dom.Element对象设置对象值的。
		//也可以指定 DataBinding 设置对象的。可继承抽象类： org.apache.cxf.databinding.AbstractDataBinding
		//具体源码可查看:org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor
		Header tokenHeader = new SoapHeader(new QName(""), element);
//		tokenHeader.setDataBinding()
		headers.add(tokenHeader);
	}

}
