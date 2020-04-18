package com.matrix.join.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName ResumeEducationEntity
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/21 21:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resume_education")
public class ResumeEducationEntity  implements Serializable {

    private static final long serialVersionUID = -7333705312085361602L;

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
     * 毕业学校
     */
    private String school;

    /**
     * 专业
     */
    private String specialty;

    /**
     * 学位
     */
    private Byte degree;

    /**
     * 开始时间
     */
    private String dateFrom;

    /**
     * 截止时间
     */
    private String dateTo;

    /**
     * 附加信息
     */
    private  String remark;
}
