package com.matrix.join.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.matrix.join.constant.BasicConstant;
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
