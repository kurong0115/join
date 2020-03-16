package com.matrix.join.dto;

import com.matrix.join.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName UserInfoDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/28 14:28
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = -8881955220857061291L;

    /**
     * 用户信息
     */
    private UserEntity user;

    /**
     * 令牌
     */
    private String token;
}
