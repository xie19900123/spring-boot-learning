package cn.lqdev.learning.chapter30.biz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.lqdev.learning.chapter30.biz.entity.User;

/**
 * 资源类
 * @author oKong
 *
 */
public interface UserDao extends PagingAndSortingRepository<User,Long>{

	List<User> findById(Long id);
	
	//使用自动命名规则进行查询服务 
	List<User> findByCodeAndName(String code,String name);
	
	//使用@Query进行 自定义sql编写
	//nativeQuery=true,正常的sql语法
	//负责是hsql语法
	@Query(value="select * from user where code = ?1",nativeQuery=true)
	List<User> queryByCode(String code);
	
	//分页
	Page<User> findByCode(String code,Pageable pageable);
}
