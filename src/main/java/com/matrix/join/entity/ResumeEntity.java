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

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName ResumeEntity
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/21 20:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resume")
@Accessors(chain = true)
public class ResumeEntity implements Serializable {

    private static final long serialVersionUID = 7148793798820766425L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    private BigInteger userId;

    /**
     * 简历编号
     */
    private BigInteger resumeId;

    /**
     * 照片
     */
    private String icon;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 学历
     */
    private Byte education;

    /**
     * 工作经验
     */
    private Byte workExperience;

    /**
     * 婚姻状况
     */
    private Byte maritalStatus;

    /**
     * 求职意向
     */
    private String jobIntension;

    /**
     * 意向城市
     */
    private String cityIntension;

    /**
     * 自我介绍
     */
    private String selfIntroduction;

    /**
     * 具备技能
     */
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
