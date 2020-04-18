package com.matrix.join.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.DeliveryRecordEntity;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.vo.DeliveryRecordVO;

import java.math.BigInteger;

/**
 * @ClassName DeliveryRecordService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 15:04
 * @Version 1.0
 */
public interface DeliveryRecordService extends IService<DeliveryRecordEntity> {

    /**
     * 保存投递信息
     * @param deliveryRecordEntity
     * @return
     */
    DeliveryRecordEntity saveDeliveryRecord(DeliveryRecordEntity deliveryRecordEntity);

    /**
     * 查询投递
     * @param jobName
     * @param recordId
     * @param state
     * @param sender
     * @param receiver
     * @param page
     * @return
     */
    IPage<DeliveryRecordVO> listDeliveryRecord(String jobName, BigInteger recordId, Byte state, BigInteger sender, BigInteger receiver, Page<DeliveryRecordVO> page);

    /**
     * 修改投递状态
     * @param userId
     * @param recordId
     * @param state
     * @return
     */
    int updateDeliveryState(BigInteger userId, BigInteger recordId, Byte state);

    //IPage<DeliveryRecordVO> listDeliveryRecord(DeliveryRecordVO vo, Page<DeliveryRecordVO> page);
}
