package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.constant.MailConstant;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.dao.UserMapper;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.UserService;
import com.matrix.join.util.PrimaryKeyGenerator;
import com.matrix.join.util.RedisUtil;
import com.matrix.join.util.SecretUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Description 用户接口实现类
 * @Author Administrator
 * @Date 2020/1/31 16:28
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserEntity login(String email, String password) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.EMAIL, email);
        // 加密
        wrapper.eq(UserConstant.PASSWORD, SecretUtils.getMD5String(password));
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }

    @Override
    public UserEntity getUserByUserId(BigInteger userId) {
        if (Objects.isNull(userId)) {
            throw new JoinBizException(UserConstant.USER_ID_IS_NOT_NULL);
        }
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.USER_ID, userId);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity, String verification) {
        Objects.requireNonNull(verification, "验证码不能为空！！！");
        if (Objects.nonNull(getUserByEmail(userEntity.getEmail()))) {
            throw new JoinBizException("该邮箱已被使用！！！");
        }
        String key = redisUtil.get(userEntity.getEmail() + MailConstant.REGISTER);
        // 判断验证码的正确性
        if (!verification.equals(key)) {
            throw new JoinBizException("验证码不对！！！");
        }
        UserEntity user = userEntity.setUserId(PrimaryKeyGenerator.generatePrimaryKey())
                .setPassword(SecretUtils.getMD5String(userEntity.getPassword()));
        userMapper.insert(user);
        return user;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.EMAIL, email);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }

    @Override
    public UserEntity updateUserInfo(UserEntity userEntity) {
        Objects.requireNonNull(userEntity.getUserId(), UserConstant.USER_ID_IS_NOT_NULL);
        String password = userEntity.getPassword();
        if (password.length() != BasicConstant.MAX_PASSWORD_LENGTH) {
            userEntity.setPassword(SecretUtils.getMD5String(password));
        }
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.USER_ID, userEntity.getUserId());
        userMapper.update(userEntity, wrapper);
        return userEntity;
    }

    @Override
    public int bindCompany(BigInteger userId, BigInteger companyNo) {
        Objects.requireNonNull(userId, UserConstant.USER_ID_IS_NOT_NULL);
        Objects.requireNonNull(companyNo, "公司编号不能为空");
        UserEntity user = new UserEntity();
        user.setCompanyNo(companyNo);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.USER_ID, userId);
        return userMapper.update(user, wrapper);
    }
}
