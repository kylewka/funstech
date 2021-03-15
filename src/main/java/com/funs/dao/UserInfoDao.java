package com.funs.dao;


import com.funs.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author kylewka
 * @Date 2021/3/1 13:51
 * @Description: UserInfo的持久层
 */

@Repository
public interface UserInfoDao {

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     */
    @UpdateProvider(type = UserInfoAction.class, method = "updateUserInfo")
    Integer updateUserInfo(UserInfo userInfo);

    class UserInfoAction {
        public String updateUserInfo(final UserInfo userInfo) {
            return new SQL() {
                {
                    UPDATE("user_info");
                    if (userInfo.getNickName() != null) {
                        SET("nickname=#{nickName}");
                    }
                    if (userInfo.getSex() != null) {
                        SET("sex=#{sex}");
                    }
                    if (userInfo.getIntro() != null) {
                        SET("intro=#{intro}");
                    }
                    if (userInfo.getQQ() != null) {
                        SET("QQ=#{QQ}");
                    }
                    if (userInfo.getWechat() != null) {
                        SET("wechat=#{wechat}");
                    }
                    if (userInfo.getPhone() != null) {
                        SET("phone=#{phone}");
                    }
                    if (userInfo.getSchool() != null) {
                        SET("school=#{school}");
                    }
                    if (userInfo.getInTime() != null) {
                        SET("inTime=#{inTime}");
                    }
                    if (userInfo.getOutTime() != null) {
                        SET("outTime=#{outTime}");
                    }
                    WHERE("uid=#{uid}");
                }
            }.toString();
        }
    }

    /**
     * 获取用户个人中心信息
     *
     * @param uid 用户id
     */
    @Select("select * from user_info where uid=#{uid}")
    UserInfo getUserInfo(Integer uid);





}
