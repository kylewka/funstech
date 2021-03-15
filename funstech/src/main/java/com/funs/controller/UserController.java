package com.funs.controller;


import com.funs.domain.User;
import com.funs.service.UserService;
import com.funs.utils.EmailUtil;
import com.funs.utils.JwtUtil;
import com.funs.utils.Msg;
import com.funs.utils.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Author kylewka
 * @Date 2021/1/18 15:19
 * @Description:
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Msg login(String username, String password, HttpServletResponse response) {
        try {
            User user = userService.userLogin(username, password);
            if (user != null) {
                if (user.getStatus() == 1) {
                    String token = JwtUtil.sign(user);
                    if (token != null) {
                        Cookie cookie = new Cookie("token", token);
                        cookie.setMaxAge(3600);//设置token有效时间
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        return Msg.success("欢迎" + JwtUtil.getUsername(token) + "登录");
                    } else {
                        return Msg.fail("登录失败");
                    }
                } else if (user.getStatus() == 0) {
                    return Msg.fail("账号未激活");
                } else {
                    return Msg.fail("账号已冻结，请联系管理员");
                }
            } else {
                return Msg.fail("用户名或密码错误");
            }
        } catch (Exception e) {
            return Msg.fail("登录失败");
        }
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @PostMapping ("/regUser")
    public Msg update(@RequestBody User user) {
        try {
            //返回值若不为1则注册失败
            if (userService.regUser(user) != 1) {
                return Msg.fail("用户名已存在请更换用户名");
            } else {
                //注册成功后发送一条验证链接至注册邮箱
                EmailUtil.sendMsg(user);
                return Msg.success("注册成功,请点击邮箱链接后激活账号");
            }
        } catch (Exception e) {
            return Msg.fail("注册失败");
        }
    }

    /**
     * 账号激活接口
     *
     * @param code
     * @return
     */
    @GetMapping("/activate")
    public Msg activate(String code) {
        try {
            if (JwtUtil.verify(code)) {
                String username = JwtUtil.getUsername(code);
                if (userService.activateUser(username)) {
                    return Msg.success("激活成功");
                } else {
                    return Msg.fail("账号已激活");
                }
            } else {
                return Msg.fail("激活失败");
            }
        } catch (Exception e) {
            return Msg.fail("激活失败");
        }
    }
}
