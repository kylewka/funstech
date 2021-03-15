package com.funs.service;

import com.funs.domain.User;
import com.funs.domain.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author kylewka
 * @Date 2021/3/9 9:49
 * @Description:
 */
public interface UserInfoService {

    //修改个人信息
    Boolean updateUserInfo(UserInfo userInfo);

    //获取用户个人中心信息
    UserInfo getUserInfo(Integer uid);

}
