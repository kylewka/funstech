package com.funs.service.impl;

import com.funs.dao.FocusDao;
import com.funs.dao.UserInfoDao;
import com.funs.domain.UserInfo;
import com.funs.service.FocusService;
import com.funs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author kylewka
 * @Date 2021/3/9 9:50
 * @Description:
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    FocusDao focusDao;


    /**
     * 修改个人信息
     *
     * @param userInfo (仅修改不为空的字段)
     */
    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        Integer row = userInfoDao.updateUserInfo(userInfo);
        if (row != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取个人信息
     *
     * @param uid (从传入的token中获取)
     * @return UserInfo
     */
    @Override
    public UserInfo getUserInfo(Integer uid) {
        return userInfoDao.getUserInfo(uid);
    }

}
