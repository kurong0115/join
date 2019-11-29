package com.matrix.join.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Company
 * @Description 公司实体类
 * @Author Administrator
 * @Date 2019/11/29 11:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
	private Integer id;
	private String name;
	private String introduce;
	private String city;
	private String industry;
	private String stage;
	private String scale;

	/**
	 * 临时字段
	 */
	private String temp;
}
