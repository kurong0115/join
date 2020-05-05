package com.matrix.join.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.UserEntity;

import java.math.BigInteger;

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
    UserEntity login(String email, String password, Byte userType);

    /**
     * 根据用户编号获取用户信息
     * @param userId 用户编号
     * @return
     */
    UserEntity getUserByUserId(BigInteger userId);

    /**
     * 注册新用户
     * @param userEntity 用户信息
     * @param verification 验证码
     * @return
     */
    UserEntity saveUser(UserEntity userEntity, String verification);

    /**
     * 根据用户邮箱获取用户信息
     * @param email 邮箱
     * @return
     */
    UserEntity getUserByEmail(String email);

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    UserEntity updateUserInfo(UserEntity userEntity);

    /**
     * 绑定用户与企业
     * @param userId
     * @param companyNo
     * @return
     */
    int bindCompany(BigInteger userId, BigInteger companyNo);

    /**
     * 重置密码
     * @param email
     * @param secret
     * @return
     */
    int recoveryPassword(String email, String secret);

    /**
     * 解绑
     * @param userId
     */
    void unbound(BigInteger userId);

    /**
     * 修改密码
     * @param userId
     * @param password
     * @param oldPassword
     */
    void updatePassword(BigInteger userId, String password, String oldPassword);

    /**
     * 查询用户信息
     * @param isDel
     * @param userEntityPage
     * @return
     */
    IPage<UserEntity> listUser(String name, byte isDel, Page<UserEntity> userEntityPage);

    /**
     * 停用用户信息
     * @param userId
     * @param isDel
     */
    void stopUser(BigInteger userId, Byte isDel);
}
