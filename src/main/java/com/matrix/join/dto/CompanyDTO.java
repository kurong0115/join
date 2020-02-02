package com.matrix.join.dto;

import com.matrix.join.po.CompanyEntity;
import com.matrix.join.po.CompanyDetailEntity;
import com.matrix.join.po.CompanyImageEntity;
import com.matrix.join.po.JobEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CompanyDTO
 * @Description Company传输类
 * @Author Administrator
 * @Date 2019/11/29 19:05
 * @Version 1.0
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 5928393934189248469L;

    private CompanyEntity company;
	private CompanyDetailEntity companyDetail;
	private List<CompanyImageEntity> list;
	private List<JobEntity> jobList;
}
