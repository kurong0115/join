package com.matrix.join.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CompanyDetail {
	private Integer id;
	private Integer companyNo;
	private String corporator;
	private String registeredFund;
	private String createTime;
	private String type;
	private String status;
	private String registeredAddress;
	private String uniformCreditCode;
	private String scope;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
}
