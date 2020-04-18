package com.matrix.join.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName JobDTO
 * @Description 职位dto
 * @Author Administrator
 * @Date 2020/2/6 13:25
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class JobDTO implements Serializable {

    private static final long serialVersionUID = 6185788831233461634L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 职位名称
     */
    @NotBlank(message = "名称为必填项")
    private String name;

    /**
     * 职位编号
     */
    private BigInteger jobNo;

    /**
     * 公司编号
     */
    private BigInteger companyNo;

    /**
     * 创建人(用户编号)
     */
    @NotBlank(message = "创建人不能为空")
    private String creator;

    /**
     * 职位类型 全职 | 兼职
     */
    @NotBlank(message = "职位类型为必填项")
    private String jobType;

    /**
     * 职位分类
     */
    @NotNull(message = "职位分类为必填项")
    private Byte jobCategory;

    /**
     * 薪水
     */
    @NotBlank(message = "薪水为必填项")
    private String salary;

    /**
     * 工作经验
     */
    @NotBlank(message = "工作经验为必填项")
    private String workExperience;

    /**
     * 学历水平
     */
    @NotBlank(message = "学历水平为必填项")
    private String education;

    /**
     * 性别
     */
    @NotBlank(message = "性别为必填项")
    private String gender;

    /**
     * 工作地点
     */
    @NotBlank(message = "工作地点为必填项")
    private String city;

    /**
     * 福利
     */
    @NotBlank(message = "职位福利为必填项")
    private String benefit;

    /**
     * 简介
     */
    @NotBlank(message = "职位简介为必填项")
    private String introduce;

    /**
     * 办公地址
     */
    @NotBlank(message = "地址为必填项")
    private String address;

    /**
     * 邮箱
     */
    @Email
    @NotBlank(message = "邮箱为必填项")
    private String email;

    /**
     * 是否启用
     */
    private String isDel;

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

    /**
     * 公司图片
     */
    private String icon;

}
