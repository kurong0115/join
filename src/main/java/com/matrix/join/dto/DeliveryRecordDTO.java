package com.matrix.join.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName DeliveryRecordDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 14:45
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeliveryRecordDTO implements Serializable {

    private static final long serialVersionUID = -1688604470326159527L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 投递记录编号
     */
    private BigInteger recordId;

    /**
     * 职位编号
     */
    @NotNull(message = "职位编号不能为空")
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
    @NotNull(message = "请先登录后再尝试")
    private BigInteger sender;

    /**
     * 招聘者
     */
    @NotNull(message = "招聘不能为空")
    private BigInteger receiver;

    /**
     * 投递状态
     */
    private String state;

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
