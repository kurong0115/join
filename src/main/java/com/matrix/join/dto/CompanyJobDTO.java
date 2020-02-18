package com.matrix.join.dto;

import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.JobEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName CompanyJobDTO
 * @Description 公司职位
 * @Author Administrator
 * @Date 2020/2/18 11:38
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CompanyJobDTO {

    private CompanyDTO companyDTO;

    private CompanyEntity companyEntity;

    private JobDTO jobDTO;
}
