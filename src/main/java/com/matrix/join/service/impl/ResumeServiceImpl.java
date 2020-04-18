package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.ResumeEducationMapper;
import com.matrix.join.dao.ResumeMapper;
import com.matrix.join.dao.ResumeWorkExperienceMapper;
import com.matrix.join.entity.ResumeEducationEntity;
import com.matrix.join.entity.ResumeEntity;
import com.matrix.join.entity.ResumeWorkExperienceEntity;
import com.matrix.join.entity.UserResume;
import com.matrix.join.service.ResumeEducationService;
import com.matrix.join.service.ResumeService;
import com.matrix.join.service.ResumeWorkExperienceService;
import com.matrix.join.util.PrimaryKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName ResumeServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:38
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, ResumeEntity> implements ResumeService {

    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    ResumeEducationMapper resumeEducationMapper;

    @Autowired
    ResumeEducationService resumeEducationService;

    @Autowired
    ResumeWorkExperienceMapper resumeWorkExperienceMapper;

    @Autowired
    ResumeWorkExperienceService resumeWorkExperienceService;

    @CacheEvict(value = "resume", key = "#result.resume.userId")
    @Override
    public UserResume saveResume(UserResume userResume) {
        BigInteger resumeId = null;
        if (userResume.getResume().getResumeId() == null) {
            // 生成简历编号
            resumeId = PrimaryKeyGenerator.generatePrimaryKey();
            userResume.getResume().setResumeId(resumeId);
            BigInteger finalResumeId = resumeId;
            userResume.getResumeEducationList().stream().forEach(x -> x.setResumeId(finalResumeId));
            userResume.getResumeWorkExperienceList().stream().forEach(x -> x.setResumeId(finalResumeId));
        }
        saveOrUpdate(userResume.getResume());
        // 新增或修改教育经历
        resumeEducationService.saveOrUpdateBatch(userResume.getResumeEducationList());
        // 新增或修改教育经历
        resumeWorkExperienceService.saveOrUpdateBatch(userResume.getResumeWorkExperienceList());
        return userResume;
    }

    @Cacheable(value = "resume", key = "#userId")
    @Override
    public UserResume getResume(BigInteger userId) {
        Objects.requireNonNull(userId, "用户编号不能为空");
        ResumeEntity resume = getOne(new QueryWrapper<ResumeEntity>().eq("user_id", userId));
        List<ResumeEducationEntity> educationList = resumeEducationService.list(new QueryWrapper<ResumeEducationEntity>().eq("user_id", userId));
        List<ResumeWorkExperienceEntity> workExperienceList = resumeWorkExperienceService.list(new QueryWrapper<ResumeWorkExperienceEntity>().eq("user_id", userId));
        return UserResume.builder()
                .resume(resume)
                .resumeEducationList(educationList)
                .resumeWorkExperienceList(workExperienceList)
                .build();
    }
}
