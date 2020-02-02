package com.matrix.join.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.po.UserEntity;

/**
 * @ClassName UserService
 * @Description 用户接口
 * @Author Administrator
 * @Date 2020/1/31 16:27
 * @Version 1.0
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    UserEntity login(String email, String password);

    /**
     * 根据用户编号获取用户信息
     * @param userId
     * @return
     */
    UserEntity getUserByUserId(String userId);

    /**
     * 注册新用户
     * @param userEntity
     * @return
     */
    UserEntity saveUser(UserEntity userEntity);
}
