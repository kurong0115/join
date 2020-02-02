package com.matrix.join.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = -5442916341874138184L;

	@Id
    @javax.persistence.Id
	private Integer id;

	/**
	 * 公司编号
	 */
	private BigInteger companyNo;

    /**
     * 公司名称
     */
    @NotBlank(message = "请输入公司名称")
	private String name;

    /**
     * 公司介绍
     */
    @NotBlank(message = "请输入公司介绍")
	private String introduce;

    /**
     * 公司所在城市
     */
    @NotBlank(message = "请输入城市")
	private String city;

    /**
     * 公司所属行业
     */
    @NotBlank(message = "请输入行业")
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
    @NotEmpty(message = "请输入图标")
    private String icon;

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
