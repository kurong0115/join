package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.dto.ResumeAttachDTO;
import com.matrix.join.dto.convert.ResumeAttachConverter;
import com.matrix.join.entity.ResumeAttachEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.service.ResumeAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ResumeAttachController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/26 13:44
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/attach")
public class ResumeAttachController {

    @Autowired
    ResumeAttachService resumeAttachService;

    @Autowired
    ResumeAttachConverter resumeAttachConverter;

    @UserLogin
    @GetMapping(value = "/listResumeAttach")
    public ApiResponse<List<ResumeAttachDTO>> listResumeAttach(@RequestParam(value = "userId", required = true) BigInteger userId) {
        List<ResumeAttachEntity> attachList = resumeAttachService.list(new QueryWrapper<ResumeAttachEntity>().eq("user_id", userId));
        return ApiResponse.responseData(attachList.stream().map(x -> resumeAttachConverter.convertPOToDTO(x)).collect(Collectors.toList()));
    }

    @UserLogin
    @PostMapping(value = "/saveResumeAttach")
    public ApiResponse<ResumeAttachDTO> saveResumeAttach(@RequestBody @Valid ResumeAttachDTO resumeAttachDTO) {
        ResumeAttachEntity resumeAttachEntity = resumeAttachService.saveResumeAttach(resumeAttachConverter.convertDTOToPO(resumeAttachDTO));
        return ApiResponse.responseData(resumeAttachConverter.convertPOToDTO(resumeAttachEntity));
    }

    @UserLogin
    @PostMapping(value = "/removeResumeAttach")
    public ApiResponse<ResumeAttachDTO> removeResumeAttach(@RequestParam(value = "attachId", required = true) BigInteger attachId,
                                                           @RequestParam(value = "userId", required = true) BigInteger userId) {
        int result = resumeAttachService.removeResumeAttach(attachId, userId);
        return ApiResponse.responseData(null);
    }

    @GetMapping(value = "/getResumeAttach")
    public ApiResponse<ResumeAttachDTO> getResumeAttach(@RequestParam(value = "attachId", required = true) BigInteger attachId) {
        ResumeAttachEntity attach = resumeAttachService.getOne(new QueryWrapper<ResumeAttachEntity>().eq("attach_id", attachId));
        return ApiResponse.responseData(resumeAttachConverter.convertPOToDTO(attach));
    }


}
