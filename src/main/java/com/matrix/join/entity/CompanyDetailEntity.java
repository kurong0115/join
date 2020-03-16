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

import javax.validation.constraints.NotBlank;
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
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "company_detail")
public class CompanyDetailEntity implements Serializable {

    /**
     * 主键
     */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 公司编号
	 */
	private BigInteger companyNo;

	/**
	 * 法人代表
	 */
	@NotBlank(message = "法人代表为必填项")
	private String corporator;

	/**
	 * 注册资金
	 */
    @NotBlank(message = "法人代表为必填项")
	private String registeredFund;

	/**
	 * 创建时间
	 */
    @NotBlank(message = "创建时间为必填项")
	private String createTime;

	/**
	 * 企业类型
	 */
    @NotBlank(message = "企业类型为必填项")
	private String type;

	/**
	 * 企业状态
	 */
    @NotBlank(message = "企业状态为必填项")
	private String status;

	/**
	 * 注册地址
	 */
    @NotBlank(message = "注册地址为必填项")
	private String registeredAddress;

	/**
	 * 社会统一信用编码
	 */
    @NotBlank(message = "社会统一信用编码为必填项")
	private String uniformCreditCode;

	/**
	 * 经营范围
	 */
    @NotBlank(message = "经营范围为必填项")
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
