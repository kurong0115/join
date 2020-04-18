package com.matrix.join.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.matrix.join.entity.DeliveryRecordEntity;
import com.matrix.join.vo.DeliveryRecordVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName DeliveryRecordMapper
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 15:03
 * @Version 1.0
 */
@Repository
public interface DeliveryRecordMapper extends BaseMapper<DeliveryRecordEntity> {

    /**
     * 查询投递记录
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<DeliveryRecordVO> listDeliveryRecord(@Param("page") IPage<DeliveryRecordVO> page, @Param(Constants.WRAPPER) Wrapper<DeliveryRecordVO> queryWrapper);


    //IPage<DeliveryRecordVO> listDeliveryRecord(@Param("page") IPage<DeliveryRecordVO> page, @Param("vo") DeliveryRecordVO vo);
}
