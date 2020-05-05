package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.dto.CompanyDTO;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.Pagination;
import com.matrix.join.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/listCompany")
    public ApiResponse<List<CompanyDTO>> listCompany(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
                                                     @RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "city", required = false) String city,
                                                     @RequestParam(value = "scale", required = false, defaultValue = "0") byte scale,
                                                     @RequestParam(value = "stage", required = false, defaultValue = "0") byte stage,
                                                     @RequestParam(value = "isDel", required = false, defaultValue = "3") byte isDel) {
        IPage<CompanyEntity> companyList = companyService.listCompany(name, city, scale, stage, isDel, new Page<CompanyEntity>(pageNum, pageSize));
        return new ApiResponse<List<CompanyDTO>>().builder().code(200).message("ok")
                .data(companyList.getRecords().stream().map(x -> new CompanyDTO().setCompany(x)).collect(Collectors.toList())).pagination(Pagination.convertPage(companyList)).build();
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

    @GetMapping(value = "/stopCompany")
    @UserLogin(userType = UserConstant.ADMIN)
    public ApiResponse<Object> stopCompany(@RequestParam(name = "companyNo") BigInteger companyNo,
                                           @RequestParam(name = "isDel") Byte isDel) {
        companyService.stopCompany(companyNo, isDel);
        return ApiResponse.responseData(null);
    }

}
