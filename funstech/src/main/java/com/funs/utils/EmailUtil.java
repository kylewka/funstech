package com.funs.utils;

import com.funs.domain.User;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author kylewka
 * @Date 2021/3/8 16:48
 * @Description: TODO:邮箱验证码，采用SecureRandom真随机数
 */
public class EmailUtil {

    private static String uri="http://127.0.0.1:8080/funstech/api/user/activate?code=";

    public static void sendMsg(User user) throws EmailException {
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.qq.com");//邮箱的SMTP服务器，qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        email.addTo(user.getEmail());//发送目标邮箱
        email.setFrom("funstech@vip.qq.com","FunS趣科技");//发送人的邮箱，用户名
        email.setAuthentication("funstech@vip.qq.com",
                "pbygauehnnwabicb");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject("FunS趣科技邮箱验证");//设置发送主题
        String code=uri+JwtUtil.sign(user);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        email.setMsg("<h2>尊敬的"+user.getUsername()+"</h2>\n" +
                "       感谢您使用FunS趣科技，请确认是您本人注册。\n" +
                "       确认无误后，请点击如下链接激活账号(有效期为 1 小时): <a href="+code+">点此激活</a>\n" +
                "       若非本人操作，您的密码可能已泄露，请尽快修改账号密码或联系我们，联系客服。\n" +
                "\n" +
                "\n" +
                "<h3>FROM\n" +
                "FunS趣科技\n" +
                ""+df.format(new Date())+"</h3>");//设置发送内容
        email.send();//进行发送
    }
}
