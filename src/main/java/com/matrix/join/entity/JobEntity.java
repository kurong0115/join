package com.matrix.join.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "job")
@Accessors(chain = true)
public class JobEntity implements Serializable {

    private static final long serialVersionUID = 5190497195919807497L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 职位编号
     */
    private BigInteger jobNo;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 公司编号
     */
    private BigInteger companyNo;

    /**
     * 创建人(用户编号)
     */
    private BigInteger creator;

    /**
     * 职位类型 全职 | 兼职
     */
    private Byte jobType;

    /**
     * 职位分类
     */
    private Byte jobCategory;

    /**
     * 薪水
     */
    private Byte salary;

    /**
     * 工作经验
     */
    private Byte workExperience;

    /**
     * 学历水平
     */
    private Byte education;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 工作地点
     */
    private String city;

    /**
     * 福利
     */
    private String benefit;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用
     */
    @TableField(value = "is_del")
    private Byte isDel;

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
