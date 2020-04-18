package com.matrix.join.controller;

import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MailController
 * @Description 邮箱
 * @Author Administrator
 * @Date 2020/2/2 15:48
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping(value = "/sendSimpleEmail")
    public ApiResponse<String> sendSimpleEmail(@RequestParam(value = "email", required = true) String email) {
        mailService.sendSimpleMessage(email);
        return new ApiResponse<String>().builder().code(200).message("ok").build();
    }

    @PostMapping(value = "/sendRecoveryEmail")
    public ApiResponse<Object> sendRecoveryEmail(@RequestParam(value = "email", required = true) String email) {
        mailService.sendRecoveryEmail(email);
        return ApiResponse.responseData(null);
    }
}
