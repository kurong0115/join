package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.dto.JobDTO;
import com.matrix.join.dto.convert.DTOConverter;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.JobService;
import com.matrix.join.util.BindResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @ClassName JobController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/7 15:42
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    JobService jobService;

    @UserLogin
    @PostMapping("/saveJob")
    public ApiResponse<JobDTO> saveJob(@RequestBody @Valid JobDTO dto, BindingResult bindingResult) {
        String errorMessage = BindResultUtil.resolveError(bindingResult);
        if (!Objects.isNull(errorMessage)) {
            throw new JoinBizException(errorMessage);
        }
        JobEntity jobEntity = jobService.saveJob(DTOConverter.parse(dto, JobEntity.class));
        return new ApiResponse<JobDTO>().builder()
                .code(200).message("ok").data(DTOConverter.convert(jobEntity, JobDTO.class)).build();
    }

    @GetMapping("/listJob")
    public ApiResponse<JobDTO> listJob(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "salary", required = false, defaultValue = "0") byte salary,
                                       @RequestParam(value = "workExperience", required = false, defaultValue = "0") byte workExperience,
                                       @RequestParam(value = "education", required = false, defaultValue = "0") byte education,
                                       @RequestParam(value = "city", required = false) String city,
                                       @RequestParam(value = "jobCategory", required = false, defaultValue = "0") byte jobCategory,
                                       @RequestParam(value = "gender", required = false, defaultValue = "0") byte gender) {
        IPage<JobEntity> jobList = jobService.listJob(name,
                jobCategory, city, salary, workExperience, education, gender, new Page<JobEntity>(pageNum, pageSize));
        return null;
    }
}
