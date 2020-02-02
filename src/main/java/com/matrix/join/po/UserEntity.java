package com.matrix.join.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.join.constant.BasicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName UserEntity
 * @Description 用户实体类
 * @Author Administrator
 * @Date 2020/1/31 16:13
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -7785531680180656228L;

    /**
     * 用户主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private BigInteger userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "请输入6-20位的字符")
    private String password;

    /**
     * 邮箱
     */
    @Size(max = 32, message = "长度超过限制")
    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空")
    @Size(min = 11, max = 11, message = "该手机号码不符合规范")
    private String phone;

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
