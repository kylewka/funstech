package com.funs.dao;

import com.funs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author kylewka
 * @Date 2021/1/15 17:16
 * @Description: User持久层
 */

@Repository
public interface UserDao {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT * from user " +
            "WHERE username=#{username} " +
            "AND password=#{password}")
    User userLogin(@Param("username") String username, @Param("password") String password);


    /**
     * 注册
     * 插入 user 表
     * 字段 username,password,email
     * INSERT IGNORE INTO 如果数据库中已经存在相同的记录，则忽略当前新数据
     * @param user
     * @return Integer
     */
    @Insert("INSERT IGNORE INTO user(username,password,email) " +
            "VALUES(#{username},#{password},#{email})")
    Integer regUser(User user);


    /**
     * 激活账号
     * @param username
     */
    @Update("UPDATE user " +
            "SET status=1 " +
            "WHERE username=#{username}")
    Integer activateUser(String username);

}
