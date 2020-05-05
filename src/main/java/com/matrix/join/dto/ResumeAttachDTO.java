package com.matrix.join.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName ResumeAttachDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/26 13:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResumeAttachDTO implements Serializable {

    private static final long serialVersionUID = 2669972164465410949L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private BigInteger userId;

    /**
     * 附件编号
     */
    private BigInteger attachId;

    /**
     * 附件地址
     */
    @NotBlank(message = "访问地址不能为空")
    private String url;

    /**
     * 文件名
     */
    private String fileName;

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
