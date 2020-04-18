package com.matrix.join.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName ResumeEducationDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/23 14:34
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResumeEducationDTO implements Serializable {

    private static final long serialVersionUID = -7472518835128299438L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    @NotNull(message = "创建人不能为空")
    private BigInteger userId;

    /**
     * 简历编号
     */
    @NotNull(message = "您暂未登录")
    private BigInteger resumeId;

    /**
     * 毕业学校
     */
    @NotBlank(message = "毕业学校不能为空")
    @Length(max = 64, message = "毕业学校名称超过最长长度")
    private String school;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空")
    @Length(max = 64, message = "专业名称超过最长长度")
    private String specialty;

    /**
     * 学位
     */
    @NotBlank(message = "学位不能为空")
    private String degree;

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空")
    @Length(max = 32)
    private String dateFrom;

    /**
     * 截止时间
     */
    @NotBlank(message = "截止时间不能为空")
    @Length(max = 32)
    private String dateTo;

    /**
     * 附加信息
     */
    @NotBlank(message = "请输入必填信息")
    private  String remark;
}
