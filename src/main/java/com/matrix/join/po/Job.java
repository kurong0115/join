package com.matrix.join.po;

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
    private Byte salary;
    private Byte workExperience;
    private Byte education;
    private String city;
    private String benefit;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
}
