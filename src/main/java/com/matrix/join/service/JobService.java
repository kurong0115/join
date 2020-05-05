package com.matrix.join.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.JobEntity;

import java.math.BigInteger;

/**
 * @ClassName JobService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/7 15:43
 * @Version 1.0
 */
public interface JobService extends IService<JobEntity> {

    /**
     * 创建job
     * @param jobEntity
     * @return
     */
    JobEntity saveJob(JobEntity jobEntity);

    /**
     * 分页条件查询职位信息
     * @param name
     * @param jobCategory
     * @param city
     * @param salary
     * @param workExperience
     * @param education
     * @param gender
     * @param jobType
     * @param jobEntityPage
     * @return
     */
    IPage<JobEntity> listJob(String name, byte jobCategory, String city, byte salary, byte workExperience, byte education, byte gender, byte jobType, BigInteger creator, byte isDel, Page<JobEntity> jobEntityPage);

    /**
     * 删除职位信息
     * @param jobNo
     * @param userId
     * @return
     */
    int removeJob(BigInteger jobNo, BigInteger userId);

    /**
     * 修改职位信息
     * @param jobEntity
     * @return
     */
    JobEntity updateJob(JobEntity jobEntity);

    /**
     * 根据职位编号获取职位信息
     * @param jobNo
     * @return
     */
    JobEntity getJob(BigInteger jobNo);

    /**
     * 停用职位信息
     * @param jobNo
     * @param creator
     */
    void disableJob(BigInteger jobNo, BigInteger creator);

    /**
     * 改变状态
     * @param jobNo
     * @param isDel
     */
    void stopCompany(BigInteger jobNo, Byte isDel);
}
