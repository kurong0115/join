package com.matrix.join.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.join.mapper.CompanyMapper;
import com.matrix.join.po.Company;
import com.matrix.join.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName CompanyServiceImpl
 * @Description 公司接口实现类
 * @Author Administrator
 * @Date 2020/1/31 11:11
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
