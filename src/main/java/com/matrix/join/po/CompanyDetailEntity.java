package com.matrix.join.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName CompanyDetail
 * @Description 公司工商信息
 * @Author Administrator
 * @Date 2019/11/29 18:56
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailEntity implements Serializable {
	private Integer id;
	private BigInteger companyNo;
	private String corporator;
	private String registeredFund;
	private String createTime;
	private String type;
	private String status;
	private String registeredAddress;
	private String uniformCreditCode;
	private String scope;

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
