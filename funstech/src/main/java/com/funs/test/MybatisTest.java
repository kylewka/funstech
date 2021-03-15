package com.funs.test;

import com.funs.dao.UserInfoDao;
import com.funs.domain.UserInfo;
import com.funs.service.FocusService;
import com.funs.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @Author kylewka
 * @Date 2021/3/5 23:00
 * @Description:
 */
public class MybatisTest {

    ApplicationContext ac;

    @Before
    public void before(){
        ac = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void test(){
        UserInfoService userInfoService = (UserInfoService) ac.getBean("userInfoService");
        UserInfo userInfo =new UserInfo();
        userInfo.setUid(18);
        userInfo.setIntro("更新简介");
        userInfoService.updateUserInfo(userInfo);
        System.out.println("数据库更新成功");
    }


    @Test
    public void test1() {
        UserInfoDao userInfoDao = (UserInfoDao) ac.getBean("userInfoDao");
        UserInfo userInfo = userInfoDao.getUserInfo(18);
        System.out.println(userInfo);

    }

    /**
     * 获取关注
     */
    @Test
    public void test2() {
        FocusService focusService = (FocusService) ac.getBean("focusService");
        List<Map<String, Object>> focus = focusService.getFocus(3, 1, 4);
        for (Map<String, Object> u:focus
             ) {
            System.out.println(u);
        }
    }

    /**
     *获取用户的粉丝数和关注数
     */
    @Test
    public void test3() {
        FocusService focusService = (FocusService) ac.getBean("focusService");
        System.out.println("关注数 ："+focusService.focusCount(1));
        System.out.println("粉丝数 ："+focusService.fansCount(1));
    }
}
