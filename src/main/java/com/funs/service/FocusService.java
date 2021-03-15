package com.funs.service;

import java.util.List;
import java.util.Map;

public interface FocusService {
    //添加关注
    Boolean addFocus(Integer uid,Integer fansId);

    //获取关注列表
    List<Map<String, Object>> getFocus(Integer uid, Integer page, Integer pageSize);

    //获取粉丝列表
    List<Map<String, Object>> getFans(Integer uid,Integer page,Integer pageSize);

    //获取用户粉丝数
    Integer focusCount(Integer uid);

    //获取用户的粉丝数
    Integer fansCount(Integer uid);
}
