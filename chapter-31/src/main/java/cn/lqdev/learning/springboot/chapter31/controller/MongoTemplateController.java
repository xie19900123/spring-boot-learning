package cn.lqdev.learning.springboot.chapter31.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.springboot.chapter31.biz.entity.NotifyMsg;
import cn.lqdev.learning.springboot.chapter31.biz.service.NotifyMsgService;
import lombok.extern.slf4j.Slf4j;

/**
 * mongoTemplate 示例
 * @author oKong
 *
 */
@RestController
@RequestMapping("/template")
@Slf4j
public class MongoTemplateController {

	@Autowired
	NotifyMsgService notifyMsgService;
	
	@PostMapping("/add")
	public NotifyMsg add(NotifyMsg msg) {
		log.info("mongoTemplate方式新增：{}", msg);
		return notifyMsgService.saveNotifyMsg(msg); 
	}
	
	@PostMapping("del/{id}")
	public NotifyMsg del(@PathVariable String id) {
		log.info("mongoTemplate方式删除：{}", id);
		return notifyMsgService.delNotifyMsgById(id);
	}
	
	@GetMapping("/find/{no}")
	public NotifyMsg findNotifyMsgByNo(@PathVariable String no){
		log.info("mongoTemplate方式查找：notifyNo-{}", no);
		return notifyMsgService.findNotifyMsgByNo(no);
	}
	
	@GetMapping("/find/list/{date}")
	public List<NotifyMsg> findNotifyMsgByDate(@PathVariable String date){
		log.info("mongoTemplate方式查找：notifyDate-{}", date);
		return notifyMsgService.findNotifyMsgByDate(date);
	}
}
