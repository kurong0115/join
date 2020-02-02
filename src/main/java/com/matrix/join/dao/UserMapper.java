package com.matrix.join.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matrix.join.po.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description 用户接口
 * @Author Administrator
 * @Date 2020/1/31 16:29
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

}
