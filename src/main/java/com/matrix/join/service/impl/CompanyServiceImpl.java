package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.CompanyMapper;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

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
}
