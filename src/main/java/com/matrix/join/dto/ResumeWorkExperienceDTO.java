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
import java.math.BigInteger;

/**
 * @ClassName ResumeWorkExperienceDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/23 14:59
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResumeWorkExperienceDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    @NotNull(message = "您暂未登录")
    private BigInteger userId;

    /**
     * 简历编号
     */
    private BigInteger resumeId;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    @Length(max = 64)
    private String companyName;

    /**
     * 职位名称
     */
    @NotBlank(message = "职位名称不能为空")
    @Length(max = 32, message = "职位名称超过最长长度")
    private String jobName;

    /**
     * 所在城市
     */
    @NotBlank(message = "所在城市不能为空")
    @Length(max = 32, message = "所在城市超过最长长度")
    private String city;

    /**
     * 任职时间
     */
    @NotBlank(message = "任职时间不能为空")
    @Length(max = 32)
    private String dateFrom;

    /**
     * 离职时间
     */
    @NotBlank(message = "离职时间不能为空")
    @Length(max = 32)
    private String dateTo;

    /**
     * 备注
     */
    @NotBlank(message = "负责内容不能为空")
    @Length(max = 2048)
    private String remark;
}
