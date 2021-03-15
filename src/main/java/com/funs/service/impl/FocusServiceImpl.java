package com.funs.service.impl;

import com.funs.dao.FocusDao;
import com.funs.domain.UserInfo;
import com.funs.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("focusService")
public class FocusServiceImpl implements FocusService {
    @Autowired
    FocusDao focusDao;

    /**
     * 添加关注
     */
    @Override
    public Boolean addFocus(Integer uid, Integer fansId) {

        return focusDao.addFocus(uid,fansId)!=1;
    }

    /**
     * 获取关注列表
     *
     * @param uid      用户id
     * @param page     页码
     * @param pageSize 页面大小
     * @return List<UserInfo>集合
     */
    @Override
    public List<Map<String, Object>> getFocus(Integer uid, Integer page, Integer pageSize) {
        List<Map<String, Object>> focus = new ArrayList<>();
        List<UserInfo> UserInfos = focusDao.getFocus(uid, (page - 1) * pageSize, pageSize);
        //调用getMaps以剔除一些不需要的字段
        return getMaps(focus, UserInfos);
    }

    /**
     * 获取粉丝列表
     *
     * @param uid      用户id
     * @param page     页码
     * @param pageSize 页面大小
     * @return List<UserInfo>集合
     */
    @Override
    public List<Map<String, Object>> getFans(Integer uid, Integer page, Integer pageSize) {
        List<Map<String, Object>> fans = new ArrayList<>();
        List<UserInfo> UserInfos = focusDao.getFans(uid, (page - 1) * pageSize, pageSize);
        return getMaps(fans, UserInfos);
    }

    /**
     * 过滤 关注/粉丝 列表中不需要的字段
     * 保留字段uid nickName avatar
     * @param map
     * @param userInfos
     * @return
     */
    private List<Map<String, Object>> getMaps(List<Map<String, Object>> map, List<UserInfo> userInfos) {
        for (UserInfo userInfo : userInfos) {
            Map<String, Object> fan = new HashMap<>();
            fan.put("uid", userInfo.getUid());
            fan.put("nickName", userInfo.getNickName());
            fan.put("avatar", userInfo.getAvatar());
            map.add(fan);
        }
        return map;
    }

    @Override
    public Integer focusCount(Integer uid) {
        return focusDao.focusCount(uid);
    }

    @Override
    public Integer fansCount(Integer uid) {
        return focusDao.fansCount(uid);
    }
}
