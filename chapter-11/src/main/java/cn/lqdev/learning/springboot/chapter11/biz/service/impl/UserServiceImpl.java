package cn.lqdev.learning.springboot.chapter11.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.lqdev.learning.springboot.chapter11.biz.dao.UserDao;
import cn.lqdev.learning.springboot.chapter11.biz.entity.User;
import cn.lqdev.learning.springboot.chapter11.biz.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oKong
 * @since 2018-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
