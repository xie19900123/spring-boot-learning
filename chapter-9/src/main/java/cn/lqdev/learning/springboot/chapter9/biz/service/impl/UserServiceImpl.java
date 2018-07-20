package cn.lqdev.learning.springboot.chapter9.biz.service.impl;

import cn.lqdev.learning.springboot.chapter9.biz.entity.User;
import cn.lqdev.learning.springboot.chapter9.biz.dao.UserDao;
import cn.lqdev.learning.springboot.chapter9.biz.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
