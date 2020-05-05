package com.matrix.join.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.matrix.join.annotation.PassToken;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.UserService;
import com.matrix.join.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * @ClassName LoginInterceptor
 * @Description 登录拦截器
 * @Author Administrator
 * @Date 2019/11/8 10:23
 * @Version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod method = (HandlerMethod)handler;
        if (method.hasMethodAnnotation(PassToken.class)){
            PassToken passToken = method.getMethodAnnotation(PassToken.class);
            if (!passToken.required()){
                return true;
            }
        }
        if (method.hasMethodAnnotation(UserLogin.class)){
            UserLogin userLogin = method.getMethodAnnotation(UserLogin.class);
            if (!userLogin.required()){
                return true;
            }
            if (token == null){
                throw new JoinBizException("token失效，请重新登录");
            }
            String id = JWT.decode(token).getAudience().get(0);
            UserEntity user = userService.getUserByUserId(BigInteger.valueOf(Long.decode(id)));
            if (user == null){
                throw new JoinBizException("用户不存在");
            }
            if (userLogin.userType() == UserConstant.ADMIN && user.getType() != UserConstant.ADMIN) {
                throw new JoinBizException("权限不足!!!");
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtUtils.SECRET)).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new JoinBizException("token不对");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
