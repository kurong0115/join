package com.matrix.join.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName Job
 * @Description 职位
 * @Author Administrator
 * @Date 2019/12/24 17:17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Integer id;
    private String name;
    private BigInteger companyNo;
    private String creator;
    private Byte jobType;
    private Byte jobCategory;
    private Byte salary;
    private Byte workExperience;
    private Byte education;
    private Byte gender;
    private String city;
    private String benefit;

    /**
     * 创建时间
     */
    @JSONField(format = BasicConstant.DATA_PATTERN)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JSONField(format = BasicConstant.DATA_PATTERN)
    private Timestamp gmtModified;
}
