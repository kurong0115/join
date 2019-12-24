package com.matrix.join.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName CompanyImage
 * @Description 公司图片
 * @Author Administrator
 * @Date 2019/11/29 18:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyImage implements Serializable {

	private Integer id;
	private BigInteger companyNo;
	private String url;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
}
