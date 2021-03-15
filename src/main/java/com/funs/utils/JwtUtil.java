package com.funs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.funs.domain.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author kylewka
 * @Date 2021/3/4 12:49
 * @Description: Java web token 工具类
 */
public class JwtUtil {
    /**
     * TODO 正式运行时修改为60分钟
     */
    private static final long EXPIRE_TIME= 60*60*1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET="f26e587c28064d0e855e72c0a6a0e618";
    /**
     * 生成签名,30min后过期
     * @param user
     * @return 加密的token
     */
    public static String sign(User user) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 附带username，userId信息，生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("username", user.getUsername())
                .withClaim("uid",user.getUid())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println("认证通过：");
//            System.out.println("username: " + jwt.getClaim("username").asString());
//            System.out.println("过期时间：" + jwt.getExpiresAt());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     * @return token中包含的用户ID
     */
    public static Integer getUid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
