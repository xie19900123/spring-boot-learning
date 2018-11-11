package cn.lqdev.learning.springboot.cxf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import cn.lqdev.learning.springboot.cxf.config.WsConst;
import cn.lqdev.learning.springboot.cxf.entity.AuthorDto;
import cn.lqdev.learning.springboot.cxf.entity.Sex;


@WebService(
		 targetNamespace = WsConst.NAMESPACE_URI, //wsdl命名空间 
		 name = "authorPortType",                 //portType名称 客户端生成代码时 为接口名称
		 serviceName = "authorService",           //服务name名称
		 portName = "authorPortName",             //port名称
		 endpointInterface = "cn.lqdev.learning.springboot.cxf.service.AuthorService")//指定发布webservcie的接口类，此类也需要接入@WebService注解
public class AuthorServiceImpl implements AuthorService{

	@Override
	public AuthorDto getAuthor(String name) {
		AuthorDto author = new AuthorDto();
    	author.setBirthday("1990-01-23");
    	author.setName("姓名：" + name);
    	author.setSex(Sex.MALE);
    	author.setHobby(Arrays.asList("电影","旅游"));
    	author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
    	return author;
	}

	@Override
	public List<AuthorDto> getAuthorList() {
		List<AuthorDto> resultList = new ArrayList<>();
		AuthorDto author = new AuthorDto();
    	author.setBirthday("1990-01-23");
    	author.setName("姓名：oKong");
    	author.setSex(Sex.MALE);
    	author.setHobby(Arrays.asList("电影","旅游"));
    	author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
    	resultList.add(author);
    	resultList.add(author);
		return resultList;
	}

	@Override
	public String getAuthorString(String name) {
		AuthorDto author = getAuthor(name);
		return author.toString();
	}
}
