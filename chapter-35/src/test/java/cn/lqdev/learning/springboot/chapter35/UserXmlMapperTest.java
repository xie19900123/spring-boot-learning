package cn.lqdev.learning.springboot.chapter35;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lqdev.learning.springboot.chapter35.biz.entity.StatusEnum;
import cn.lqdev.learning.springboot.chapter35.biz.entity.User;
import cn.lqdev.learning.springboot.chapter35.biz.mapper.UserXmlMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * xml配置测试类
 * @author oKong
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("xml")
@Slf4j
public class UserXmlMapperTest {

	@Autowired
	UserXmlMapper userMpper;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setCode("102");
		user.setName("name002");
		user.setStatus(StatusEnum.ENABLE);
		
		//新增
		userMpper.insert(user);
	}
	
	@Test
	public void testQueryOne() {
		User user = userMpper.queryOne(1L);
		log.info("id为1的查询结果为：{}", user);
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setCode("102");
		user.setName("testUpdate");
		user.setStatus(StatusEnum.ENABLE);
		userMpper.insert(user);
		
		User userUpd = userMpper.queryOne(user.getId());
		userUpd.setName("更新name");
		userMpper.update(userUpd);
		
		Assert.assertEquals("更新失败",userUpd.getName(), userMpper.queryOne(user.getId()).getName());
	}
	
	@Test
	public void testParamSelect() {
		String code = "102";
		List<User> list = userMpper.queryByParams(code);
		
		log.info("查询编码为002,查询结果为：{}条,结果集为：{}",list.size(), Arrays.toString(list.toArray()));
	}
}
