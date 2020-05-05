package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.dto.CompanyJobDTO;
import com.matrix.join.dto.JobDTO;
import com.matrix.join.dto.convert.DTOConverter;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.Pagination;
import com.matrix.join.service.CompanyService;
import com.matrix.join.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    CompanyService companyService;

    @UserLogin
    @PostMapping("/saveJob")
    public ApiResponse<JobDTO> saveJob(@RequestBody @Valid JobDTO dto) {
        JobEntity jobEntity = jobService.saveJob(DTOConverter.parse(dto, JobEntity.class));
        return new ApiResponse<JobDTO>().builder()
                .code(200).message("ok").data(DTOConverter.convert(jobEntity, JobDTO.class)).build();
    }

    @UserLogin
    @DeleteMapping("/removeJob")
    public ApiResponse<String> removeJob(@RequestParam(value = "jobNo", required = true) BigInteger jobNo,
                                         @RequestParam(value = "userId", required = true) BigInteger userId) {
        int result = jobService.removeJob(jobNo, userId);
        if (result == 0) {
            return new ApiResponse<String>().builder().code(200).message("ok").message("删除失败").build();
        }
        return new ApiResponse<String>().builder().code(200).message("ok").message("删除成功").build();
    }

    @GetMapping("/listJob")
    public ApiResponse<List<JobDTO>> listJob(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "city", required = false) String city,
                                             @RequestParam(value = "salary", required = false, defaultValue = "0") byte salary,
                                             @RequestParam(value = "workExperience", required = false, defaultValue = "0") byte workExperience,
                                             @RequestParam(value = "education", required = false, defaultValue = "0") byte education,
                                             @RequestParam(value = "jobCategory", required = false, defaultValue = "0") byte jobCategory,
                                             @RequestParam(value = "gender", required = false, defaultValue = "0") byte gender,
                                             @RequestParam(value = "jobType", required = false, defaultValue = "0") byte jobType,
                                             @RequestParam(value = "creator", required = false) BigInteger creator,
                                             @RequestParam(value = "isDel", required = false, defaultValue = "3") byte isDel){
        IPage<JobEntity> jobList = jobService.listJob(name, jobCategory, city,
                salary, workExperience, education, gender,jobType, creator, isDel, new Page<JobEntity>(pageNum, pageSize));
        return new ApiResponse<List<JobDTO>>().builder().code(200).message("ok").data(jobList.getRecords().stream()
                .map(x -> DTOConverter.convert(x, JobDTO.class)).collect(Collectors.toList())).pagination(Pagination.convertPage(jobList)).build();
    }

    @UserLogin
    @PutMapping("/updateJob")
    public ApiResponse<JobDTO> updateJob(@RequestBody @Valid JobDTO dto) {
        JobEntity jobEntity = jobService.updateJob(DTOConverter.parse(dto, JobEntity.class));
        return new ApiResponse<JobDTO>().builder().code(200).message("ok").data(DTOConverter.convert(jobEntity, JobDTO.class)).build();
    }

    @GetMapping(value = "/getJobInfo")
    public ApiResponse<CompanyJobDTO> getJobInfo(@RequestParam(value = "jobNo", required = true) BigInteger jobNo) {
        JobEntity jobEntity = jobService.getJob(jobNo);
        CompanyEntity companyEntity = companyService.getCompanyByNo(jobEntity.getCompanyNo());
        return new ApiResponse<CompanyJobDTO>().builder().code(200).message("ok")
                .data(CompanyJobDTO.builder().companyEntity(companyEntity)
                .jobDTO(DTOConverter.convert(jobEntity, JobDTO.class)).build()).build();
    }

    @PostMapping(value = "/disableJob")
    @UserLogin
    public ApiResponse<Object> disableJob(@RequestParam(name = "jobNo") BigInteger jobNo,
                                          @RequestParam(name = "creator") BigInteger creator){
        jobService.disableJob(jobNo, creator);
        return ApiResponse.responseData(null);
    }

    @GetMapping(value = "/stopJob")
    @UserLogin(userType = UserConstant.ADMIN)
    public ApiResponse<Object> stopCompany(@RequestParam(name = "jobNo") BigInteger jobNo,
                                           @RequestParam(name = "isDel") Byte isDel) {
        jobService.stopCompany(jobNo, isDel);
        return ApiResponse.responseData(null);
    }
}
