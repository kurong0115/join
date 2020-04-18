package com.matrix.join.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName ResumeWorkExperienceEntity
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/21 20:59
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resume_work_experience")
public class ResumeWorkExperienceEntity implements Serializable {

    private static final long serialVersionUID = 1300031130669973238L;

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
     * 公司名称
     */
    private String companyName;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 任职时间
     */
    private String dateFrom;

    /**
     * 离职时间
     */
    private String dateTo;

    /**
     * 备注
     */
    private String remark;
}
