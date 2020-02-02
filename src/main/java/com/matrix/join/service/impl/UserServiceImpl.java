package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.UserMapper;
import com.matrix.join.po.UserEntity;
import com.matrix.join.service.UserService;
import com.matrix.join.util.PrimaryKeyGenerator;
import com.matrix.join.util.SecretUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable(value = "users", key = "#email")
    @Override
    public UserEntity login(String email, String password) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        // 加密
        wrapper.eq("password", SecretUtils.getMD5String(password));
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }

    @Cacheable(value = "users", key = "#userId")
    @Override
    public UserEntity getUserByUserId(String userId) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        UserEntity user = userEntity.setUserId(PrimaryKeyGenerator.generatePrimaryKey());
        userMapper.insert(user);
        return user;
    }
}
