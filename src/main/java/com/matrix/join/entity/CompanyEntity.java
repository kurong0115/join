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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Accessors(chain = true)
@TableName(value = "company")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = -5442916341874138184L;

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
     * 公司名称
     */
    @NotBlank(message = "请输入公司名称")
    @Length(max = 64, message = "公司名称长度超过限制")
	private String name;

    /**
     * 公司介绍
     */
    @NotBlank(message = "请输入公司简介")
    @Length(max = 2048, message = "公司介绍长度超过限制")
	private String introduce;

    /**
     * 公司所在城市
     */
    @NotBlank(message = "请输入城市")
    @Length(max = 16, message = "城市长度超过限制")
	private String city;

    /**
     * 公司所属行业
     */
    @NotBlank(message = "请输入行业")
    @Length(max = 16, message = "行业长度超过限制")
	private String industry;

	/**
	 * 融资情况
	 */
	@NotNull(message = "请输入融资情况")
	private Integer stage;

    /**
     * 人数
     */
    @NotNull(message = "请输入人数阶级")
	private Integer scale;

    /**
     * 图标
     */
    @NotBlank(message = "请输入图标")
    private String icon;

    /**
     * 法人代表
     */
    @NotBlank(message = "法人代表为必填项")
    @Length(max = 16, message = "法人代表长度超过限制")
    private String corporator;

    /**
     * 注册资金
     */
    @NotBlank(message = "注册资金为必填项")
    @Length(max = 16, message = "注册资金长度超过限制")
    private String registeredFund;

    /**
     * 创建时间
     */
    @NotBlank(message = "创建时间为必填项")
    @Length(max = 16, message = "创建时间长度超过限制")
    private String createTime;

    /**
     * 企业类型
     */
    @NotBlank(message = "企业类型为必填项")
    @Length(max = 16, message = "企业类型长度超过限制")
    private String type;

    /**
     * 企业状态
     */
    @NotBlank(message = "企业状态为必填项")
    @Length(max = 16, message = "企业状态长度超过限制")
    private String status;

    /**
     * 注册地址
     */
    @NotBlank(message = "注册地址为必填项")
    @Length(max = 128, message = "注册地址长度超过限制")
    private String registeredAddress;

    /**
     * 社会统一信用编码
     */
    @NotBlank(message = "社会统一信用编码为必填项")
    @Length(min = 16, max = 16, message = "社会统一信用编码长度非法")
    private String uniformCreditCode;

    /**
     * 经营范围
     */
    @NotBlank(message = "经营范围为必填项")
    @Length(max = 2555, message = "经营范围长度超过限制")
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
