package cn.lqdev.learning.springboot.chapter35.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lqdev.learning.springboot.chapter35.biz.entity.User;

/**
 * xml映射
 * @author oKong
 *
 */
public interface UserXmlMapper {

	User queryOne(Long id);
	
	int insert(User user);
	
	void update(User user);
	
	void delete(Long id);
		
	List<User> queryByParams(@Param("code")String code);
}
