package com.matrix.join.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.matrix.join.entity.UserEntity;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName JwtUtils
 * @Description 用户验证token
 * @Author Administrator
 * @Date 2019/11/8 10:40
 * @Version 1.0
 */
public class JwtUtils {

    public static final String SECRET = "7c6f5de8726a485b96a34593b5d0a16f";

    /**
     * 根据成功登录的用户信息获取token
     * @param user
     * @return
     */
    public static String getToken(UserEntity user){
        String token = null;
        if (Objects.isNull(user)){
            return null;
        }
        token = JWT.create().withAudience(user.getUserId().toString()).sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static void main(String[] args) {
        String id = JWT.decode(getToken(new UserEntity().setUserId(BigInteger.valueOf(111)))).getAudience().get(0);
        System.out.println(id);
    }
}
