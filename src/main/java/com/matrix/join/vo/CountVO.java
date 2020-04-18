package com.matrix.join.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName CountVO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/3 10:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CountVO {
    private Integer companyCount;

    private Integer memberCount;

    private Integer jobCount;

    private Integer applicationCount;
}
