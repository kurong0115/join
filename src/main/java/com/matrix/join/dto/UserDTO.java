package com.matrix.join.dto;

import com.matrix.join.po.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/1 15:41
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 5665403300058594240L;

    @Valid
    private UserEntity userEntity;

}
