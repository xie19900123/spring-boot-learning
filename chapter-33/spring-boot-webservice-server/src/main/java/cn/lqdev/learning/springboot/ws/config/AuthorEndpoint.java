package cn.lqdev.learning.springboot.ws.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cn.lqdev.learning.springboot.ws.webservice.Author;
import cn.lqdev.learning.springboot.ws.webservice.AuthorListRequest;
import cn.lqdev.learning.springboot.ws.webservice.AuthorListResponse;
import cn.lqdev.learning.springboot.ws.webservice.AuthorRequest;
import cn.lqdev.learning.springboot.ws.webservice.AuthorResponse;
import cn.lqdev.learning.springboot.ws.webservice.Sex;

/**
 * 创建endpoint 类似于创建controller了。
 * @author oKong
 *
 */
@Endpoint
public class AuthorEndpoint {

    @PayloadRoot(namespace = WsConst.NAMESPACE_URI, localPart = "authorRequest")
    @ResponsePayload 
    public AuthorResponse getAuthor(@RequestPayload AuthorRequest authorReq){
    	AuthorResponse resp = new AuthorResponse();
    	Author author = new Author();
    	author.setBirthday("1990-01-23");
    	author.setName("姓名：" + authorReq.getName());
    	author.setSex(Sex.FEMALE);
    	author.getHobby().addAll(Arrays.asList("电影","旅游"));
    	author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
    	resp.setAuthor(author);
    	return resp;
    }
    
    @PayloadRoot(namespace = WsConst.NAMESPACE_URI, localPart = "authorListRequest")
    @ResponsePayload 
    public AuthorListResponse getAuthorList(@RequestPayload AuthorListRequest request){
    	AuthorListResponse resp = new AuthorListResponse();
    	Author author = new Author();
    	author.setBirthday("1990-01-23");
    	author.setName("姓名：oKong");
    	author.setSex(Sex.FEMALE);
    	author.getHobby().addAll(Arrays.asList("电影","旅游"));
    	author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
    	resp.getAuthor().add(author);
    	resp.getAuthor().add(author);
    	return resp;
    }
}
