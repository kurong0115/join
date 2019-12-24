package com.matrix.join.dto;

import com.matrix.join.po.Company;
import com.matrix.join.po.CompanyDetail;
import com.matrix.join.po.CompanyImage;
import com.matrix.join.po.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {
	private Company company;
	private CompanyDetail companyDetail;
	private List<CompanyImage> list;
	private List<Job> jobList;
}
