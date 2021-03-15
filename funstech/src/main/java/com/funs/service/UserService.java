package com.funs.service;


import com.funs.domain.User;

/**
 * @Author kylewka
 * @Date 2021/1/21 15:57
 * @Description: User业务层
 */
public interface UserService {

    //登录
    User userLogin(String username, String password);

    //注册
    Integer regUser(User user);

    //激活账号
    Boolean activateUser(String username);
}
