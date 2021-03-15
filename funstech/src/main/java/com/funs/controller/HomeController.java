package com.funs.controller;

import com.funs.domain.UserInfo;
import com.funs.service.UserInfoService;
import com.funs.utils.JwtUtil;
import com.funs.utils.Msg;
import com.funs.utils.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author kylewka
 * @Date 2021/3/9 10:12
 * @Description:
 */

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/updateInfo")
    public Msg updateInfo(@RequestBody UserInfo userInfo, HttpServletRequest request){
        Cookie token = TokenInterceptor.getCookieByName(request, "token");
        userInfo.setUid(JwtUtil.getUid(token.getValue()));
        Boolean info = userInfoService.updateUserInfo(userInfo);
        if (!info){
            return Msg.fail("fail");
        }
        return Msg.success("success");
    }

    @GetMapping("/getUserInfo")
    public Msg getUserInfo(HttpServletRequest request){
        try {
            //获取传入cookie中的token值
            Cookie token = TokenInterceptor.getCookieByName(request, "token");
            //通过token中存储的uid获取对应的用户信息
            UserInfo userInfo = userInfoService.getUserInfo(JwtUtil.getUid(token.getValue()));
            return Msg.success("success").add("userInfo", userInfo);
        }catch (Exception e){
            return Msg.fail("fail");
        }
    }
 }
