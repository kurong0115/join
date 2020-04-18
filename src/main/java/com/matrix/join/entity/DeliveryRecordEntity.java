package com.matrix.join.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName DeliveryRecordEntity
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 11:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "delivery_record")
public class DeliveryRecordEntity implements Serializable {

    private static final long serialVersionUID = -6439426032277826700L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 投递记录编号
     */
    private BigInteger recordId;

    /**
     * 职位编号
     */
    private BigInteger jobId;

    /**
     * 在线简历编号
     */
    private BigInteger resumeId;

    /**
     * 附件编号
     */
    private BigInteger attachId;

    /**
     * 投递者
     */
    private BigInteger sender;

    /**
     * 招聘者
     */
    private BigInteger receiver;

    /**
     * 投递状态
     */
    private Byte state;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = BasicConstant.DATA_PATTERN)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = BasicConstant.DATA_PATTERN)
    private Timestamp gmtModified;
}
