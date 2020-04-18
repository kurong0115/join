package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.ResumeEducationMapper;
import com.matrix.join.entity.ResumeEducationEntity;
import com.matrix.join.service.ResumeEducationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ResumeEducationServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:37
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResumeEducationServiceImpl extends ServiceImpl<ResumeEducationMapper, ResumeEducationEntity>
        implements ResumeEducationService {
}
