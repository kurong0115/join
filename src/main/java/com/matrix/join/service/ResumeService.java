package com.matrix.join.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.ResumeEntity;
import com.matrix.join.entity.UserResume;

import java.math.BigInteger;

/**
 * @ClassName ResumeService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:33
 * @Version 1.0
 */
public interface ResumeService extends IService<ResumeEntity> {

    /**
     * 新增用户在线简历信息
     * @param userResume
     * @return
     */
    UserResume saveResume(UserResume userResume);

    /**
     * 获取用户简历信息
     * @param userId
     * @return
     */
    UserResume getResume(BigInteger userId);
}
