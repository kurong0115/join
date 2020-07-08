package com.matrix.join.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.matrix.join.entity.UserEntity;

import java.util.Objects;

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
        if (Objects.isNull(user)){
            return null;
        }
        return JWT.create().withAudience(user.getUserId().toString()).sign(Algorithm.HMAC256(SECRET));
    }

}
