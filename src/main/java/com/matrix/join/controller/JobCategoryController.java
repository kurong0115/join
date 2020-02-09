package com.matrix.join.controller;

import com.matrix.join.dto.JobCategoryDTO;
import com.matrix.join.dto.convert.DTOConverter;
import com.matrix.join.entity.JobCategoryEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName JobCategoryController
 * @Description 职位类别
 * @Author Administrator
 * @Date 2020/2/5 10:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
public class JobCategoryController {

    @Autowired
    JobCategoryService jobCategoryService;

    @Cacheable(value = "categoryList")
    @GetMapping(value = "/listAllCategory")
    public ApiResponse<List<JobCategoryDTO>> listAllCategory() {
        List<JobCategoryEntity> categoryEntityList = jobCategoryService.list();
        return new ApiResponse<List<JobCategoryDTO>>().builder().code(200).message("ok")
                .data(categoryEntityList.stream().map(x -> DTOConverter.convert(x, JobCategoryDTO.class)).collect(Collectors.toList())).build();
    }
}
