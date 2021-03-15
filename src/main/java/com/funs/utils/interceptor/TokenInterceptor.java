package com.funs.utils.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.funs.utils.JwtUtil;
import com.funs.utils.Msg;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author kylewka
 * @Date 2021/3/4 13:51
 * @Description: Token拦截器
 */

@Component
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * 拦截器针对访问控制器进行拦截
     * 及 @RequestMapping(value = {"/test"})
     * 简而言说就是访问方法的url
     * 应用：可以作为权限的判断，
     */

    /**
     * 在拦截器的3个方法中
     * 只有preHandle()方法是运行在控制器(Controller)之前的
     * 另2个方法是运行在控制器之后的
     * 所以，只有preHandle()具有真正意义的“拦截”功能
     * 该方法的返回值是boolean类型的
     * 当返回true时表示放行，返回false时将阻止继续向后执行
     * 即控制器并不会被执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了token拦截器！");
        //缺少了application/json导致返拦截器返回的值为空
        response.setContentType("application/json; charset=utf-8");
        //调用自定义方法获取cookie种的token的值
        Cookie cookie=getCookieByName(request,"token");
        //如果已经登录，不拦截
        if (null != cookie) {
            //验证token是否正确
            boolean result = JwtUtil.verify(cookie.getValue());
            System.out.println(JwtUtil.getUsername(cookie.getValue()+"通过认证"));
            if (!result) {
                ObjectMapper objectMapper = new ObjectMapper();
                String msg=objectMapper.writeValueAsString(Msg.fail("账号认证失败，请重新登录"));
                response.getWriter().append(msg);
                return false;
            }
            return true;
        }else {
            //未登录
            ObjectMapper objectMapper = new ObjectMapper();
            String msg=objectMapper.writeValueAsString(Msg.fail("未登录"));
            response.getWriter().append(msg);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /**
     * 根据名字获取cookie
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie =  cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}