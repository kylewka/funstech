package com.funs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author kylewka
 * @Date 2021/1/21 16:00
 * @Description: User实体类
 */
public class User implements Serializable {

    //用户编号
    private Integer uid;
    //用户角色0管理员1普通用户
    private Integer role;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //创建时间
    private Date gmt_create;
    //状态0可用1封禁
    private Integer status;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gmt_create=" + gmt_create +
                ", status=" + status +
                '}';
    }
}
