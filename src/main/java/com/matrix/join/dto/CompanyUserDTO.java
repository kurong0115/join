package com.matrix.join.dto;

import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName CompanyUserDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/28 11:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CompanyUserDTO {

    private UserEntity user;

    private CompanyEntity company;
}
