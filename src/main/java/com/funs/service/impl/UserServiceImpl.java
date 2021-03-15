package com.funs.service.impl;

import com.funs.dao.UserDao;
import com.funs.domain.User;
import com.funs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author kylewka
 * @Date 2021/1/21 16:01
 * @Description: User业务层实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public User userLogin(String username, String password) {
        System.out.println(username + "调用了userLogin");
        return userdao.userLogin(username, password);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public Integer regUser(User user) {
        System.out.println("执行了userService的regUser方法");
        return userdao.regUser(user);
    }

    /**
     * 激活用户
     *
     * @param username 用户名(从token中获取)
     * @return
     */
    @Override
    public Boolean activateUser(String username) {
        return userdao.activateUser(username) == 1;
    }



}
