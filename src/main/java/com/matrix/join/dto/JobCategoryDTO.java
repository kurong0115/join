package com.matrix.join.dto;

import com.matrix.join.entity.JobCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @ClassName JobCategoryDTO
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/5 14:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class JobCategoryDTO implements Serializable {

    private static final long serialVersionUID = 8357474926934337825L;

    @Valid
    private JobCategoryEntity jobCategoryEntity;
}
