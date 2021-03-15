package com.funs.test;

import com.funs.domain.User;
import com.funs.utils.JwtUtil;
import org.junit.Test;

/**
 * @Author kylewka
 * @Date 2021/3/4 16:57
 * @Description:
 */
public class TokenTest {

    @Test
    public void test0(){
        User user =new User();
        user.setUid(999);
        user.setUsername("qqq999");
        String token = JwtUtil.sign(user);
        System.out.println(token);
        String p= JwtUtil.getUsername(token)+"   "+JwtUtil.getUid(token);
        System.out.println(p);
        if(JwtUtil.verify(token)){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }
    }

    @Test
    public void test1(){
        if(JwtUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTU2MzAyMDIsInVzZXJuYW1lIjoiamluYXFpeWFuZyJ9.Fv3c5g_reSfZR5OkUB3OjZBTSur-8QmyE5Vorbo38Cg")){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }
    }

}
