package com.matrix.join.controller;

import com.auth0.jwt.JWT;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.common.EducationEnum;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.dao.CompanyMapper;
import com.matrix.join.dto.CompanyDTO;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @ClassName CompanyController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/29 10:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @UserLogin
    @PostMapping("/saveCompany")
    public ApiResponse<CompanyDTO> saveCompany(@RequestBody @Valid CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyService.saveCompany(companyDTO.getCompany());
        return ApiResponse.responseData(new CompanyDTO().setCompany(companyEntity));
    }

    @GetMapping("/getCompanyInfo")
    public ApiResponse<CompanyDTO> getCompanyInfo(@RequestParam(name = "companyNo", required = true) BigInteger companyNo) {
        CompanyEntity companyEntity = companyService.getCompanyByNo(companyNo);
        return ApiResponse.responseData(new CompanyDTO().setCompany(companyEntity));
    }

    @GetMapping("/getCompanyByUniformCreditCode")
    public ApiResponse<CompanyDTO> getCompanyByUniformCreditCode(@RequestParam(name = "uniformCreditCode", required = true) String uniformCreditCode) {
        CompanyEntity companyEntity = companyService.getCompanyByUniformCreditCode(uniformCreditCode);
        return ApiResponse.responseData(new CompanyDTO().setCompany(companyEntity));
    }

}
