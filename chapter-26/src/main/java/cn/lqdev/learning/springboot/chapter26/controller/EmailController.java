package cn.lqdev.learning.springboot.chapter26.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件
 * @author oKong
 *
 */
@RestController
public class EmailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 纯文本格式
	 * @return
	 */
	@GetMapping("/simple")
	public String simpleSend() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("499452441@qq.com");
		message.setTo("499452441@qq.com");
		message.setSubject("主题：来自oKong邮件");
		message.setText("公众号：一枚趔趄的猿(lqdevOps)，作者：oKong");
		mailSender.send(message);		
		return "发送成功!";
	}
	
	@GetMapping("/attach")
	public String attachSend() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("499452441@qq.com");
		helper.setTo("499452441@qq.com");	
		helper.setSubject("主题：来自oKong邮件(带附件)");
		helper.setText("(含附件)公众号：一枚趔趄的猿(lqdevOps)，作者：oKong");
		//添加附件
		File qrCode = new File("wxgzh8cm.jpg");
		//建议文件带上后缀，可支持在线预览 
		helper.addAttachment("公众号二维码.jpg", qrCode);
		mailSender.send(mimeMessage);
		return "附件邮件发送成功!";
	}
	
	/**
	 * html格式
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/html")
	public String htmlSend() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("499452441@qq.com");
		helper.setTo("499452441@qq.com");	
		helper.setSubject("主题：来自oKong邮件(带附件)");
		helper.setText("<html><body><div>(含附件)公众号：一枚趔趄的猿(lqdevOps)，作者：oKong</div><div><img src='cid:winxin'></div></body></html>",true);
		
		//抄送人
//		helper.setCc("");
		//密送人
//		helper.setBcc("");
		//添加附件
		File qrCode = new File("wxgzh8cm.jpg");
		//建议文件带上后缀，可支持在线预览 
		helper.addAttachment("公众号二维码.jpg", qrCode);
		helper.addInline("winxin", qrCode);

		mailSender.send(mimeMessage);
		return "附件邮件发送成功!";
	}
	
	@GetMapping("/template")
	public String template(String userName) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("499452441@qq.com");
		helper.setTo("499452441@qq.com");	
		helper.setSubject("主题：" + userName + ",你有一封来自oKong邮件(From模版)");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", StringUtils.isEmpty(userName) ? "oKong" : userName);
		String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("mail.ftl"), model);
		helper.setText(templateString,true);
		//抄送人
//		helper.setCc("");
		//密送人
//		helper.setBcc("");
		//添加附件
		File qrCode = new File("wxgzh8cm.jpg");
		//建议文件带上后缀，可支持在线预览 
		helper.addAttachment("公众号二维码.jpg", qrCode);
		helper.addInline("winxin", qrCode);
		mailSender.send(mimeMessage);
		
		return "模版文件发送成功!";
	}
	//自动注入
	@Autowired 
	freemarker.template.Configuration freemarkerConfig;

}
