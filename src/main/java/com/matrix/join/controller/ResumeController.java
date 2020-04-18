package com.matrix.join.controller;

import com.matrix.join.annotation.UserLogin;
import com.matrix.join.dto.UserResumeDTO;
import com.matrix.join.dto.convert.UserResumeConverter;
import com.matrix.join.entity.UserResume;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

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
}
