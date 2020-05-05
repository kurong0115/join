package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.dao.ResumeAttachMapper;
import com.matrix.join.entity.ResumeAttachEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.ResumeAttachService;
import com.matrix.join.util.FileUtils;
import com.matrix.join.util.PrimaryKeyGenerator;
import com.matrix.join.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName ResumeAttachServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/26 13:42
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResumeAttachServiceImpl extends ServiceImpl<ResumeAttachMapper, ResumeAttachEntity>
        implements ResumeAttachService {

    @Autowired
    ResumeAttachMapper resumeAttachMapper;

    @Value("${file.head.path}")
    String path;

    @Value("${file.head.file-path}")
    String filePath;

    @Value("${file.head.project-path}")
    String projectPath;

    @Override
    public ResumeAttachEntity saveResumeAttach(ResumeAttachEntity resumeAttachEntity) {
        List<ResumeAttachEntity> attachList = list(new QueryWrapper<ResumeAttachEntity>().eq("user_id", resumeAttachEntity.getUserId()));
        if (attachList.size() >= BasicConstant.MAX_SIZE_ATTACH) {
            FileUtils.removeFile(StringUtils.concat(projectPath, resumeAttachEntity.getUrl()));
            throw new JoinBizException("已达到附件简历数量上限");
        }
        resumeAttachEntity.setAttachId(PrimaryKeyGenerator.generatePrimaryKey());
        resumeAttachMapper.insert(resumeAttachEntity);
        return resumeAttachEntity;
    }

    @Override
    public int removeResumeAttach(BigInteger attachId, BigInteger userId) {
        Objects.requireNonNull(attachId, "附件编号不能为空");
        Objects.requireNonNull(userId, "用户编号不能为空");
        UpdateWrapper<ResumeAttachEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("attach_id", attachId);
        updateWrapper.eq("user_id", userId);
        // 查询存放路径
        ResumeAttachEntity attach = getOne(new QueryWrapper<ResumeAttachEntity>().eq("attach_id", attachId));
        // 删除文件
        FileUtils.removeFile(StringUtils.concat(projectPath, attach.getUrl()));
        return resumeAttachMapper.delete(updateWrapper);
    }

    @Override
    public void deleteResumeAttach(BigInteger attachId) {
        UpdateWrapper<ResumeAttachEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("attach_id", attachId);
        resumeAttachMapper.delete(updateWrapper);
    }

    @Override
    public IPage<ResumeAttachEntity> listAttach(BigInteger userId, BigInteger attachId, Page<ResumeAttachEntity> page) {
        QueryWrapper<ResumeAttachEntity> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(userId)) {
            wrapper.eq("user_id", userId);
        }
        if (Objects.nonNull(attachId)) {
            wrapper.eq("attach_id", attachId);
        }
        return resumeAttachMapper.selectPage(page, wrapper);
    }
}
