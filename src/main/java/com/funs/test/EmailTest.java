package com.funs.test;

import com.funs.domain.User;
import com.funs.utils.EmailUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * @Author kylewka
 * @Date 2021/3/8 15:43
 * @Description:
 */
public class EmailTest {

    @Test
    public void test() {
        try {
            User user = new User();
            user.setUsername("wangkaihang");
            user.setPassword("123456");
            user.setEmail("937511531@qq.com");
            EmailUtil.sendMsg(user);
        } catch (Exception e) {
            System.out.println("发送异常");
        }
    }
}
