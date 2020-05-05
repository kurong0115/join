package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.dao.JobMapper;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.CompanyService;
import com.matrix.join.service.JobService;
import com.matrix.join.service.UserService;
import com.matrix.join.util.PrimaryKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @ClassName JobServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/7 15:44
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl extends ServiceImpl<JobMapper, JobEntity> implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Override
    public JobEntity saveJob(JobEntity jobEntity) {
        if (Objects.isNull(jobEntity.getCreator())) {
            throw new JoinBizException("创建人不能为空");
        }
        UserEntity user = userService.getUserByUserId(jobEntity.getCreator());
        if (Objects.isNull(user) || Objects.isNull(user.getCompanyNo())){
            throw new JoinBizException("该用户不能创建职位，请先完善公司信息再尝试");
        }
        // 生成职位编号
        jobEntity.setJobNo(PrimaryKeyGenerator.generatePrimaryKey());
        jobEntity.setCompanyNo(user.getCompanyNo());
        int result = jobMapper.insert(jobEntity);
        return jobEntity;
    }

    @Override
    public IPage<JobEntity> listJob(String name, byte jobCategory, String city, byte salary, byte workExperience, byte education, byte gender, byte jobType, BigInteger creator, byte isDel, Page<JobEntity> jobEntityPage) {
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.likeRight("name", name);
        }
        if (!StringUtils.isEmpty(city)) {
            wrapper.likeRight("city", city);
        }
        if (jobCategory != 0) {
            wrapper.eq("job_category", jobCategory);
        }
        if (salary != 0) {
            wrapper.eq("salary", salary);
        }
        if (workExperience != 0) {
            wrapper.eq("work_experience", workExperience);
        }
        if (education != 0) {
            wrapper.ge("education", education);
        }
        if (gender != 0) {
            wrapper.eq("gender", gender);
        }
        if (jobType != 0) {
            wrapper.eq("job_type", jobType);
        }
        if (Objects.nonNull(creator)) {
            wrapper.eq("creator", creator);
        }
        if (isDel == BasicConstant.DISABLED || isDel == BasicConstant.ABLE) {
            wrapper.eq("is_del", isDel);
        }
        Page<JobEntity> page = jobMapper.selectPage(jobEntityPage, wrapper);
        if (Objects.nonNull(page) && Objects.nonNull(page.getRecords())) {
            page.getRecords().stream().forEach(x -> {
                CompanyEntity company = companyService.getCompanyByNo(x.getCompanyNo());
                if (Objects.nonNull(company)) {
                    x.setIcon(company.getIcon());
                }
            });
        }

        return page;
    }

    @Override
    @CacheEvict(value = "job", key = "#jobNo")
    public int removeJob(BigInteger jobNo, BigInteger userId) {
        if (Objects.isNull(jobNo) || Objects.isNull(userId)) {
            throw new JoinBizException(("请输入完整信息"));
        }
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("job_no", jobNo);
        wrapper.eq("creator", userId);
        return jobMapper.delete(wrapper);
    }

    @Override
    @CachePut(value = "job", key = "#result.jobNo")
    public JobEntity updateJob(JobEntity jobEntity) {
        if (Objects.isNull(jobEntity.getJobNo()) || Objects.isNull(jobEntity.getCreator())) {
            throw new JoinBizException(("请输入完整信息"));
        }
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("job_no", jobEntity.getJobNo());
        wrapper.eq("creator", jobEntity.getCreator());
        jobMapper.update(jobEntity, wrapper);
        return jobEntity;
    }

    @Override
    @Cacheable(value = "job", key = "#jobNo")
    public JobEntity getJob(BigInteger jobNo) {
        if (Objects.isNull(jobNo)) {
            throw new JoinBizException(("职位编号不能为空"));
        }
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("job_no", jobNo);
        return jobMapper.selectOne(wrapper);
    }

    @CacheEvict(value = "job", key = "#jobNo")
    @Override
    public void disableJob(BigInteger jobNo, BigInteger creator) {
        Objects.requireNonNull(jobNo, "职位编号不能为空");
        Objects.requireNonNull(creator, "操作人不能为空");
        UpdateWrapper<JobEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("job_no", jobNo);
        updateWrapper.eq("creator", creator);
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobNo(jobNo);
        jobEntity.setCreator(creator);
        jobEntity.setIsDel(BasicConstant.DISABLED);
        jobMapper.update(jobEntity, updateWrapper);
    }

    @CacheEvict(value = "job", key = "#jobNo")
    @Override
    public void stopCompany(BigInteger jobNo, Byte isDel) {
        Objects.requireNonNull(jobNo, "职位编号不能为空");
        JobEntity entity = new JobEntity();
        entity.setCompanyNo(jobNo);
        entity.setIsDel(isDel);
        UpdateWrapper<JobEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("job_no", jobNo);
        jobMapper.update(entity, wrapper);
    }
}
