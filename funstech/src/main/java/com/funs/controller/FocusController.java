package com.funs.controller;


import com.funs.service.FocusService;
import com.funs.service.UserInfoService;
import com.funs.utils.JwtUtil;
import com.funs.utils.Msg;
import com.funs.utils.interceptor.TokenInterceptor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/focus")
public class FocusController {

    @Autowired
    FocusService focusService;
    @Autowired
    UserInfoService userInfoService;

    /**
     * 关注用户
     *
     * @param uid     关注的id
     * @param request 获取token中的uid
     */
    @PostMapping("/addFocus")
    public Msg addFocus(Integer uid, HttpServletRequest request) {
        try {
            //通过Jwt工具获取token中的uid(操作者的id)
            Integer fansId = JwtUtil.getUid(
                    //在request请求的cookie中读取对应的token的值
                    Objects.requireNonNull(TokenInterceptor.getCookieByName(request, "token")).getValue());
            //判断所传入的uid所对应的用户是否为空
            if (userInfoService.getUserInfo(uid) != null) {
                return focusService.addFocus(uid, fansId) ?
                        Msg.fail("关注失败") : Msg.success("关注成功");
            } else {
                return Msg.fail("关注失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Msg.fail("关注失败");
        }
    }

    /**
     * 获取关注列表
     * @param map 传入page，pageSize
     * @param request 获取token中的uid
     */
    @PostMapping("/getFocus")
    public Msg getFocus(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        try {
            Integer uid = JwtUtil.getUid(
                    //在request请求的cookie中读取对应的token的值
                    Objects.requireNonNull(TokenInterceptor.getCookieByName(request, "token")).getValue());
            List<Map<String, Object>> focus = focusService
                    .getFocus(uid, (Integer) map.get("page"), (Integer) map.get("pageSize"));
            return Msg.success("success").add("focus", focus);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("关注列表获取失败");
        }
    }

    @PostMapping("/getFans")
    public Msg getFans(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        try {
            Integer uid = JwtUtil.getUid(
                    //在request请求的cookie中读取对应的token的值
                    Objects.requireNonNull(TokenInterceptor.getCookieByName(request, "token")).getValue());
            List<Map<String, Object>> fans = focusService
                    .getFans(uid, (Integer) map.get("page"), (Integer) map.get("pageSize"));
            return Msg.success("success").add("fans", fans);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("粉丝列表获取失败");
        }
    }

}
