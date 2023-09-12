package com.cleansoft.demo.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
    //token 过期时间
    private static final long EXPIRE_TIME = 3*60*1000;

    //签名生成JWT
    public static String sign(String account,String password){
        Algorithm algorithm = Algorithm.HMAC256(password);
        String dontkown = JWT.create().withClaim("account", account)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(algorithm);
        return dontkown;
    }

    //使用token 用户和密码 进行内部直接jwt校验
    public static boolean verify(String token,String account,String password){
        Algorithm algorithm = Algorithm.HMAC256(password);
        //传入密钥 还有claim  生成verifier对象
        JWTVerifier verifier = JWT.require(algorithm).withClaim("account", account)
                .build();
        //校验jwt的正确性
        verifier.verify(token);
        return true;
    }
}