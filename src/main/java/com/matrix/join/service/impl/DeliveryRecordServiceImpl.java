package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.constant.MailConstant;
import com.matrix.join.dao.DeliveryRecordMapper;
import com.matrix.join.entity.*;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.*;
import com.matrix.join.util.PrimaryKeyGenerator;
import com.matrix.join.util.StringUtils;
import com.matrix.join.vo.DeliveryRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @ClassName DeliveryRecordServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 15:05
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryRecordServiceImpl extends ServiceImpl<DeliveryRecordMapper, DeliveryRecordEntity>
        implements DeliveryRecordService {

    @Autowired
    DeliveryRecordMapper deliveryRecordMapper;

    @Autowired
    ResumeService resumeService;

    @Autowired
    ResumeAttachService resumeAttachService;

    @Autowired
    JobService jobService;

    @Autowired
    MailService mailService;

    @Value("${file.head.project-path}")
    String projectPath;

    @Override
    public DeliveryRecordEntity saveDeliveryRecord(DeliveryRecordEntity deliveryRecordEntity) {
        QueryWrapper<DeliveryRecordEntity> queryWrapper = new QueryWrapper<DeliveryRecordEntity>().eq("job_id", deliveryRecordEntity.getJobId())
                .eq("sender", deliveryRecordEntity.getSender())
                .eq("receiver", deliveryRecordEntity.getReceiver());
        DeliveryRecordEntity recordEntity = getOne(queryWrapper);
        if (!Objects.isNull(recordEntity)) {
            throw new JoinBizException("您已投递该职位");
        }
        // 判断是否是在线简历
        if (Objects.isNull(deliveryRecordEntity.getAttachId())) {
            ResumeEntity resume = resumeService.getOne(new QueryWrapper<ResumeEntity>().eq("user_id", deliveryRecordEntity.getSender()));
            if (Objects.isNull(resume)) {
                throw new JoinBizException("您暂未填写在线简历信息!!!");
            }
            deliveryRecordEntity.setResumeId(resume.getResumeId());
        } else {
            // 同时向招聘者发送附件简历邮件
            ResumeAttachEntity attach = resumeAttachService.getOne(new QueryWrapper<ResumeAttachEntity>().eq("attach_id", deliveryRecordEntity.getAttachId()));
            JobEntity job = jobService.getJob(deliveryRecordEntity.getJobId());
            mailService.sendAttachMail(job.getEmail(), MailConstant.ATTACH_SUBJECT, MailConstant.ATTACH_CONTENT, StringUtils.concat(projectPath, attach.getUrl()));
        }
        deliveryRecordEntity.setRecordId(PrimaryKeyGenerator.generatePrimaryKey());
        deliveryRecordEntity.setState((byte) 0);
        deliveryRecordMapper.insert(deliveryRecordEntity);
        return deliveryRecordEntity;
    }

    @Override
    public IPage<DeliveryRecordVO> listDeliveryRecord(String jobName, BigInteger recordId, Byte state, BigInteger sender, BigInteger receiver, Page<DeliveryRecordVO> page) {
        QueryWrapper<DeliveryRecordVO> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(jobName)) {
            queryWrapper.likeRight("j.name", jobName);
        }
        if (Objects.nonNull(recordId)) {
            queryWrapper.eq("record_id", recordId);
        }
        if (Objects.nonNull(state)) {
            queryWrapper.eq("state", state);
        }
        if (Objects.nonNull(sender)) {
            queryWrapper.eq("sender", sender);
        }
        if (Objects.nonNull(receiver)) {
            queryWrapper.eq("receiver", receiver);
        }
        return deliveryRecordMapper.listDeliveryRecord(page, queryWrapper);
    }

    @Override
    public int updateDeliveryState(BigInteger userId, BigInteger recordId, Byte state) {
        UpdateWrapper<DeliveryRecordEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("record_id", recordId);
        updateWrapper.eq("receiver", userId);
        DeliveryRecordEntity recordEntity = new DeliveryRecordEntity();
        recordEntity.setState(state);
        return deliveryRecordMapper.update(recordEntity, updateWrapper);
    }

//    @Override
//    public IPage<DeliveryRecordVO> listDeliveryRecord(DeliveryRecordVO vo, Page<DeliveryRecordVO> page) {
//        QueryWrapper<DeliveryRecordVO> queryWrapper = new QueryWrapper<>();
//
//        return deliveryRecordMapper.listDeliveryRecord(page, vo);
//    }
}
