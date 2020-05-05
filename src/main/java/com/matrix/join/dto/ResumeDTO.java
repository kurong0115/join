package com.matrix.join.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName ResumeDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/23 14:23
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "在线简历基本信息")
public class ResumeDTO implements Serializable {

    private static final long serialVersionUID = 225792406318700223L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    @NotNull(message = "您暂未登录")
    @ApiModelProperty(value = "用户编号", required = true)
    private BigInteger userId;

    /**
     * 简历编号
     */
    private BigInteger resumeId;

    /**
     * 照片
     */
    @NotBlank
    @ApiModelProperty(value = "照片", required = true)
    private String icon;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名为必填项")
    @Length(max = 16, message = "姓名超过最长长度")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄为必填项")
    //@Size(min = 0, max = 200, message = "请输入正确的年龄")
    @ApiModelProperty(value = "年龄", required = true)
    private Byte age;

    /**
     * 性别
     */
    @NotBlank(message = "性别为必填项")
    @ApiModelProperty(value = "性别", required = true)
    private String gender;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码为必填项")
    @Length(min = 11, max = 11, message = "请输入正确的电话号码")
    @ApiModelProperty(value = "电话号码", required = true)
    private String phone;

    /**
     * 联系邮箱
     */
    @NotBlank(message = "邮箱为必填项")
    @Email(message = "请输入正确格式的邮箱")
    @ApiModelProperty(value = "联系邮箱", required = true)
    private String email;

    /**
     * 学历
     */
    @NotBlank(message = "学历为必填项")
    @ApiModelProperty(value = "学历", required = true)
    private String education;

    /**
     * 工作经验
     */
    @NotBlank(message = "工作经验为必填项")
    @ApiModelProperty(value = "工作经验", required = true)
    private String workExperience;

    /**
     * 婚姻状况
     */
    @NotBlank(message = "婚姻状况为必填项")
    @ApiModelProperty(value = "婚姻状况", required = true)
    private String maritalStatus;

    /**
     * 求职意向
     */
    @NotBlank(message = "求职意向为必填项")
    @Length(max = 32, message = "求职意向长度非法")
    @ApiModelProperty(value = "求职意向", required = true)
    private String jobIntension;

    /**
     * 意向城市
     */
    @NotBlank(message = "意向城市为必填项")
    @Length(max = 32, message = "意向城市长度非法")
    @ApiModelProperty(value = "意向城市", required = true)
    private String cityIntension;

    /**
     * 自我介绍
     */
    @NotBlank(message = "自我介绍为必填项")
    @Length(max = 2048, message = "自我介绍长度非法")
    @ApiModelProperty(value = "自我介绍", required = true)
    private String selfIntroduction;

    /**
     * 具备技能
     */
    @NotBlank(message = "具备技能为必填项")
    @Length(max = 2048, message = "具备技能长度非法")
    @ApiModelProperty(value = "具备技能", required = true)
    private String skill;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = BasicConstant.DATA_PATTERN, timezone="GMT+8")
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = BasicConstant.DATA_PATTERN, timezone="GMT+8")
    private Timestamp gmtModified;
}
