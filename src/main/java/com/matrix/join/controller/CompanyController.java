package com.matrix.join.controller;

import com.matrix.join.common.EducationEnum;
import com.matrix.join.dao.CompanyMapper;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.protocol.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    CompanyMapper companyMapper;

    @PostMapping("/listAll")
    public Object listAll(@RequestBody @Valid CompanyEntity company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return companyMapper.selectList(null);
    }

    @GetMapping("/response")
    public ApiResponse<EducationEnum[]> response() {
        return new ApiResponse<EducationEnum[]>().builder().code(200).message("ok").data(EducationEnum.values()).pagination(null).build();
    }

}
