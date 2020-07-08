package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.constant.MailConstant;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.dao.UserMapper;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.UserService;
import com.matrix.join.util.PrimaryKeyGenerator;
import com.matrix.join.util.RedisUtil;
import com.matrix.join.util.SecretUtils;
import com.matrix.join.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private UserMapper userMapper;

    private RedisUtil redisUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public UserEntity login(String email, String password, Byte userType) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.EMAIL, email);
        if (Objects.nonNull(userType)) {
            wrapper.eq("type", userType);
        }
        // 加密
        wrapper.eq(UserConstant.PASSWORD, SecretUtils.getMD5String(password));
        UserEntity userEntity = userMapper.selectOne(wrapper);
        if (Objects.nonNull(userEntity)) {
            userEntity.setPassword(null);
            if (userEntity.getIsDel() == BasicConstant.DISABLED) {
                throw new JoinBizException("您的账号已被停用");
            }
        }
        return userEntity;
    }

    @Cacheable(value = "user", key = "#userId")
    @Override
    public UserEntity getUserByUserId(BigInteger userId) {
        if (Objects.isNull(userId)) {
            throw new JoinBizException(UserConstant.USER_ID_IS_NOT_NULL);
        }
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.USER_ID, userId);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        if (Objects.nonNull(userEntity)) {
            userEntity.setPassword(null);
        }
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

    @CachePut(value = "user", key = "#result.userId")
    @Override
    public UserEntity updateUserInfo(UserEntity userEntity) {
        Objects.requireNonNull(userEntity.getUserId(), UserConstant.USER_ID_IS_NOT_NULL);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(UserConstant.USER_ID, userEntity.getUserId());
        userMapper.update(userEntity, wrapper);
        return userEntity;
    }

    @CacheEvict(value = "user", key = "#userId")
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

    @Override
    public int recoveryPassword(String email, String secret) {
        String code = redisUtil.get(StringUtils.concat(email, MailConstant.RECOVERY));
        Objects.requireNonNull(code, "秘钥已失效");
        if (!secret.equals(code)) {
            throw new JoinBizException("秘钥不对");
        }
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        String password = SecretUtils.getMD5String(UserConstant.DEFAULT_PASSWORD);
        updateWrapper.eq("email", email);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userMapper.update(userEntity, updateWrapper);
        redisUtil.delete(StringUtils.concat(email, MailConstant.RECOVERY));
        return 0;
    }

    @CacheEvict(value = "user", key = "#userId")
    @Override
    public void unbound(BigInteger userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCompanyNo(BasicConstant.DEFAULT_COMPANY_NO);
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(UserConstant.USER_ID, userId);
        userMapper.update(userEntity, updateWrapper);
    }

    @CacheEvict(value = "user", key = "#userId")
    @Override
    public void updatePassword(BigInteger userId, String password, String oldPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(SecretUtils.getMD5String(password));
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(UserConstant.USER_ID, userId);
        updateWrapper.eq(UserConstant.PASSWORD, SecretUtils.getMD5String(oldPassword));
        int result = userMapper.update(userEntity, updateWrapper);
        if (result <= 0 ) {
            throw new JoinBizException("原密码输入不正确!!!");
        }
    }

    @Override
    public IPage<UserEntity> listUser(String name, byte isDel, Page<UserEntity> userEntityPage) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (!org.springframework.util.StringUtils.isEmpty(name)) {
            wrapper.likeRight("name", name);
        }
        if (isDel == BasicConstant.DISABLED || isDel == BasicConstant.ABLE) {
            wrapper.eq("is_del", isDel);
        }
        return userMapper.selectPage(userEntityPage, wrapper);
    }

    @Override
    public void stopUser(BigInteger userId, Byte isDel) {
        Objects.requireNonNull(userId, "职位编号不能为空");
        UserEntity entity = new UserEntity();
        entity.setUserId(userId);
        entity.setIsDel(isDel);
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", userId);
        userMapper.update(entity, wrapper);
    }
}
