package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.dao.CompanyMapper;
import com.matrix.join.dao.UserMapper;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.service.CompanyService;
import com.matrix.join.util.PrimaryKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public CompanyEntity getCompanyByNo(BigInteger companyNo) {
        QueryWrapper<CompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("company_no", companyNo);
        return companyMapper.selectOne(wrapper);
    }

    @CacheEvict(value = "company", key = "#result.companyNo")
    @Override
    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
//        if (Objects.nonNull(getCompanyByUniformCreditCode(companyEntity.getUniformCreditCode()))) {
//            throw new JoinBizException("该公司已存在");
//        }
        if (Objects.isNull(companyEntity.getCompanyNo())) {
            BigInteger companyNo = PrimaryKeyGenerator.generatePrimaryKey();
            companyEntity.setCompanyNo(companyNo);
            companyMapper.insert(companyEntity);
            UserEntity user = new UserEntity();
            user.setCompanyNo(companyNo);
            userMapper.update(user, new UpdateWrapper<UserEntity>().eq("user_id", companyEntity.getCreator()));
        } else {
            UpdateWrapper<CompanyEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("company_no", companyEntity.getCompanyNo());
            companyMapper.update(companyEntity, updateWrapper);
        }
        return companyEntity;
    }

    @Cacheable(value = "company", key = "#result.companyNo")
    @Override
    public CompanyEntity getCompanyByUniformCreditCode(String uniformCreditCode) {
        Objects.requireNonNull(uniformCreditCode, "uniformCreditCode不能为空");
        QueryWrapper<CompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("uniform_credit_code", uniformCreditCode);
        return companyMapper.selectOne(wrapper);
    }


}
