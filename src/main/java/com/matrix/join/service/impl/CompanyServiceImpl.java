package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.CompanyMapper;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.CompanyService;
import com.matrix.join.util.PrimaryKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @ClassName CompanyServiceImpl
 * @Description 公司接口实现类
 * @Author Administrator
 * @Date 2020/1/31 11:11
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, CompanyEntity> implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public CompanyEntity getCompanyByNo(BigInteger companyNo) {
        QueryWrapper<CompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("company_no", companyNo);
        return companyMapper.selectOne(wrapper);
    }

    @Override
    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
        if (Objects.nonNull(getCompanyByUniformCreditCode(companyEntity.getUniformCreditCode()))) {
            throw new JoinBizException("该公司已存在");
        }
        companyEntity.setCompanyNo(PrimaryKeyGenerator.generatePrimaryKey());
        int result = companyMapper.insert(companyEntity);
        return companyEntity;
    }

    @Override
    public CompanyEntity getCompanyByUniformCreditCode(String uniformCreditCode) {
        Objects.requireNonNull(uniformCreditCode, "uniformCreditCode不能为空");
        QueryWrapper<CompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("uniform_credit_code", uniformCreditCode);
        return companyMapper.selectOne(wrapper);
    }
}
