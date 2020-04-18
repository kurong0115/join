package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.ResumeWorkExperienceMapper;
import com.matrix.join.entity.ResumeWorkExperienceEntity;
import com.matrix.join.service.ResumeWorkExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ResumeWorkExperienceServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:35
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResumeWorkExperienceServiceImpl extends ServiceImpl<ResumeWorkExperienceMapper, ResumeWorkExperienceEntity>
        implements ResumeWorkExperienceService {
}
