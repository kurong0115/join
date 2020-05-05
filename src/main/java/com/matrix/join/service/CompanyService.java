package com.matrix.join.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 保存公司基本信息
     * @param companyEntity
     * @return
     */
    CompanyEntity saveCompany(CompanyEntity companyEntity);

    /**
     * 根据社会信用编码获取公司信息
     * @param uniformCreditCode
     * @return
     */
    CompanyEntity getCompanyByUniformCreditCode(String uniformCreditCode);

    /**
     * 查询公司列表
     * @param name
     * @param city
     * @param scale
     * @param stage
     * @param companyEntityPage
     * @return
     */
    IPage<CompanyEntity> listCompany(String name, String city, byte scale, byte stage, byte isDel, Page<CompanyEntity> companyEntityPage);

    /**
     * 停用公司
     * @param companyNo
     */
    void stopCompany(BigInteger companyNo, Byte isDel);
}
