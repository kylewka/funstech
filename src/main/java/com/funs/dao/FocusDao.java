package com.funs.dao;

import com.funs.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FocusDao {
    /**
     * 添加关注
     * @param uid
     * @param fansId
     * @return
     */
    @Insert("INSERT INTO fans(uid,fansId) " +
            "VALUES(#{uid},#{fansId})")
    Integer addFocus(@Param("uid") Integer uid,
                     @Param("fansId") Integer fansId);

    /**
     * 获取关注列表(分页)
     *
     * @param uid,offset,limit
     */
    @Select("SELECT ui.UID,ui.NICKNAME,ui.AVATAR " +
            "FROM user_info ui,fans f " +
            "WHERE f.UID=ui.UID " +
            "AND f.FansId=#{uid} " +
            "ORDER BY f.GMT_CREATE DESC " +
            "LIMIT #{offset},#{limit}")
    List<UserInfo> getFocus(@Param("uid") Integer uid,
                            @Param("offset") Integer offset,
                            @Param("limit") Integer limit );

    /**
     * 获取用户关注总数
     *
     * @param uid 用户id
     */
    @Select("SELECT count(uid) FROM fans WHERE FansId=#{uid}")
    Integer focusCount(Integer uid);


    /**
     * 获取粉丝列表（分页）
     *
     * @param uid,offset,limit
     */
    @Select("SELECT ui.UID, ui.NICKNAME, ui.AVATAR " +
            "FROM user_info ui,fans f " +
            "WHERE f.FansId=ui.UID " +
            "AND f.uid=#{uid} " +
            "ORDER BY f.GMT_CREATE DESC " +
            "LIMIT #{offset},#{limit}")
    List<UserInfo> getFans(@Param("uid") Integer uid,
                           @Param("offset") Integer offset,
                           @Param("limit") Integer limit );
    /**
     * 获取用户关注总数
     *
     * @param uid 用户id
     */
    @Select("SELECT count(fansId) FROM fans WHERE uid=#{uid}")
    Integer fansCount(Integer uid);
}
