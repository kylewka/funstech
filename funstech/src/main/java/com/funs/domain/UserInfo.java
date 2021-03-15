package com.funs.domain;

import java.util.Date;

/**
 * @Author kylewka
 * @Date 2021/2/25 16:12
 * @Description: UserInfo实体类
 */
public class UserInfo {

    private Integer id;
    //用户编号
    private Integer uid;
    //用户昵称
    private String nickName;
    //用户头像
    private String avatar;
    //性别
    private Character sex;
    //个人简介
    private String intro;
    //邮箱
    private String email;
    //QQ
    private String QQ;
    //微信
    private String wechat;
    //手机号
    private String phone;
    //学校
    private String school;
    //入学时间
    private Date inTime;
    //毕业时间
    private Date outTime;

    public UserInfo() {
    }

    public UserInfo(Integer uid, String nickName) {
        this.uid = uid;
        this.nickName = nickName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String  getPhone() {
        return phone;
    }

    public void setPhone(String  phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", intro='" + intro + '\'' +
                ", email='" + email + '\'' +
                ", QQ='" + QQ + '\'' +
                ", wechat='" + wechat + '\'' +
                ", phone=" + phone +
                ", school='" + school + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                '}';
    }
}
