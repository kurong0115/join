package com.matrix.join.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.JobMapper;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.JobService;
import com.matrix.join.service.UserService;
import com.matrix.join.util.PrimaryKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName JobServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/7 15:44
 * @Version 1.0
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, JobEntity> implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Autowired
    UserService userService;

    @Override
    public JobEntity saveJob(JobEntity jobEntity) {
        System.out.println(jobEntity);
        if (Objects.isNull(jobEntity.getCreator())) {
            throw new JoinBizException("创建人不能为空");
        }
        UserEntity user = userService.getUserByUserId(jobEntity.getCreator());
        if (Objects.isNull(user)){
            throw new JoinBizException("用户不存在");
        }
        // 生成职位编号
        jobEntity.setJobNo(PrimaryKeyGenerator.generatePrimaryKey());
        int result = jobMapper.insert(jobEntity);
        return jobEntity;
    }

    @Override
    public IPage<JobEntity> listJob(String name, byte jobCategory, String city, byte salary, byte workExperience, byte education, byte gender, Page<JobEntity> jobEntityPage) {
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        //wrapper.like
        return null;
    }
}
