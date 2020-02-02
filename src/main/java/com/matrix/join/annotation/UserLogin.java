package com.matrix.join.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName UserLogin
 * @Description 标注用户登录后才可以进行某些操作
 * @Author Administrator
 * @Date 2019/11/8 10:16
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLogin {
    boolean required() default true;
}
