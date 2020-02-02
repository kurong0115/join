package com.matrix.join.annotation;

import java.lang.annotation.*;

/**
 * @ClassName RedisCache
 * @Description 标注此注解的方法做缓存
 * @Author Administrator
 * @Date 2019/11/14 14:55
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
}
