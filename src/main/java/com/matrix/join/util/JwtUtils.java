package com.matrix.join.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.matrix.join.po.UserEntity;

import java.util.Objects;

/**
 * @ClassName JwtUtils
 * @Description 用户验证token
 * @Author Administrator
 * @Date 2019/11/8 10:40
 * @Version 1.0
 */
public class JwtUtils {

    /**
     * 根据成功登录的用户信息获取token
     * @param user
     * @return
     */
    public static String getToken(UserEntity user){
        String token = "";
        if (Objects.isNull(user)){
            return token;
        }
        token = JWT.create().withAudience(user.getUserId().toString()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
