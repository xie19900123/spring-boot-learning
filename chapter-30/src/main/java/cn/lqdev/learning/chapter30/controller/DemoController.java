package cn.lqdev.learning.chapter30.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lqdev.learning.chapter30.biz.dao.UserDao;
import cn.lqdev.learning.chapter30.biz.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试类
 * @author oKong
 *
 */
@RestController
@Slf4j
public class DemoController {

	@Autowired
	UserDao userDao;
	
	@PostMapping("/add")
	public String addUser(User user) {
		log.info("新增用户:{}", user);
		user = userDao.save(user);
		return "新增成功，返回用户id为：" + user.getId();
	}
	
	@GetMapping("/find/{id}")
	public User findUser(@PathVariable Long id) {
		log.info("查找用户ID:{}", id);
		return userDao.findOne(id);
	}
	
	@PostMapping("/del/{id}")
	public String delUser(Long id) {
		log.info("删除用户ID:{}", id);
		userDao.delete(id);
		return "用户id为：" + id + "，已被删除!";
	}
	
	@GetMapping("/find/{code}/{name}")
	public List<User> findUserByCodeAndName(@PathVariable("code") String code, @PathVariable("name")String name) {
		log.info("命名规则方式，查找用户:编码：{}，名称：{}", code, name);
		return userDao.findByCodeAndName(code, name);
	}
	
	@GetMapping("/find/paging/{code}")
	public Page<User> findUserByCodePagin(@PathVariable("code") String code){
		log.info("分页模式，查找用户:编码：{}", code);
		//这里注意 page是从0开始的
		return userDao.findByCode(code, new PageRequest(0,10));
	}
	
	@GetMapping("/find/sql/{code}")
	public List<User> findUserByQuerySql(@PathVariable("code") String code){
		log.info("自定义sql方式，查找用户:编码：{}", code);

		return userDao.queryByCode(code);
	}
}
