package com.matrix.join.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName Company
 * @Description 公司基本信息
 * @Author Administrator
 * @Date 2019/11/29 11:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
	private Integer id;
	private BigInteger companyNo;
	private String name;
	private String introduce;
	private String city;
	private String industry;
	private Integer stage;
	private Integer scale;
	private String icon;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;

}
