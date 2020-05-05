package com.matrix.join.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.ResumeAttachEntity;

import java.math.BigInteger;

/**
 * @ClassName ResumeAttachService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/26 13:42
 * @Version 1.0
 */
public interface ResumeAttachService extends IService<ResumeAttachEntity> {

    /**
     * 保存附件简历存放信息
     * @param resumeAttachEntity
     * @return
     */
    ResumeAttachEntity saveResumeAttach(ResumeAttachEntity resumeAttachEntity);

    /**
     * 删除附件简历信息
     * @param attachId 附件编号
     * @param userId   用户编号
     * @return
     */
    int removeResumeAttach(BigInteger attachId, BigInteger userId);

    /**
     * 删除附件简历
     * @param attachId
     */
    void deleteResumeAttach(BigInteger attachId);

    /**
     * 分页查询附件
     * @param userId
     * @param attachId
     * @param page
     * @return
     */
    IPage<ResumeAttachEntity> listAttach(BigInteger userId, BigInteger attachId, Page<ResumeAttachEntity> page);
}
