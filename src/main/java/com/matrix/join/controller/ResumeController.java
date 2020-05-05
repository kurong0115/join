package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.dto.UserResumeDTO;
import com.matrix.join.dto.convert.UserResumeConverter;
import com.matrix.join.entity.ResumeEntity;
import com.matrix.join.entity.UserResume;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.Pagination;
import com.matrix.join.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/24 21:21
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserResumeConverter userResumeConverter;

    @UserLogin
    @PostMapping(value = "/saveResume")
    public ApiResponse<UserResumeDTO> saveResume(@RequestBody @Valid UserResumeDTO userResumeDTO) {
        UserResume userResume = resumeService.saveResume(userResumeConverter.convertDTOToPO(userResumeDTO));
        return ApiResponse.responseData(userResumeConverter.convertPOToDTO(userResume));
    }

    @UserLogin
    @GetMapping(value = "/getResume")
    public ApiResponse<UserResumeDTO> getResume(@RequestParam(value = "userId", required = true) BigInteger userId) {
        UserResume userResume = resumeService.getResume(userId);
        return ApiResponse.responseData(userResumeConverter.convertPOToDTO(userResume));
    }

    @UserLogin
    @GetMapping(value = "/listResume")
    public ApiResponse<List<UserResumeDTO>> listResume(@RequestParam(name = "resumeId", required = false) BigInteger resumeId,
                                                       @RequestParam(name = "userId", required = false) BigInteger userId,
                                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                       @RequestParam(name = "pageSize", required = false, defaultValue = "9") int pageSize) {
        IPage<UserResume> resumeList = resumeService.listResume(resumeId, userId, new Page<ResumeEntity>(pageNum, pageSize));
        return new ApiResponse<List<UserResumeDTO>>().builder().code(200).message("ok")
                .data(resumeList.getRecords().stream().map(x -> userResumeConverter.convertPOToDTO(x))
                .collect(Collectors.toList())).pagination(Pagination.convertPage(resumeList)).build();
    }

    @UserLogin
    @GetMapping(value = "/removeResume")
    public ApiResponse<Object> removeResume(@RequestParam(name = "resumeId") BigInteger resumeId) {
        resumeService.removeResume(resumeId);
        return ApiResponse.responseData(userResumeConverter.convertPOToDTO(null));
    }
}
