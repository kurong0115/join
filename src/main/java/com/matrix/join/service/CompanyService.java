package com.matrix.join.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.join.entity.CompanyEntity;

import java.math.BigInteger;

/**
 * @ClassName CompanyService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/31 11:09
 * @Version 1.0
 */
public interface CompanyService extends IService<CompanyEntity> {

    /**
     * 根据公司编号获取公司信息
     * @param companyNo
     * @return
     */
    CompanyEntity getCompanyByNo(BigInteger companyNo);

}
