package com.matrix.join.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName DeliveryRecordVO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 15:20
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeliveryRecordVO implements Serializable {

    private static final long serialVersionUID = -4829535033438356161L;

    private BigInteger recordId;

    private BigInteger jobId;

    private BigInteger attachId;

    private BigInteger resumeId;

    private BigInteger sender;

    private BigInteger receiver;

    private String jobName;

    private String userName;

    private Byte state;
}
