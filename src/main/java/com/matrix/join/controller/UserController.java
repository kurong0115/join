package com.matrix.join.controller;

import com.matrix.join.dto.UserDTO;
import com.matrix.join.po.UserEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.UserService;
import com.matrix.join.util.BindResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/31 16:22
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ApiResponse<UserEntity> login(@RequestParam(value = "email", required = true) String email,
                                         @RequestParam(value = "password", required = true) String password) {
        UserEntity userEntity = userService.login(email, password);
        return new ApiResponse<UserEntity>().builder().code(200).message("ok").data(userEntity).build();
    }

    @GetMapping(value = "/get")
    public ApiResponse<UserEntity> get(@RequestParam(value = "userId", required = false) String userId) throws JoinBizException {
        if (Objects.isNull(userId)) {
            throw new JoinBizException("123456");
        }
        UserEntity userEntity = userService.getUserByUserId(userId);
        return new ApiResponse<UserEntity>().builder().code(200).message("ok").data(userEntity).build();
    }

    @PostMapping(value = "/register")
    public ApiResponse<UserEntity> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        String errorMessage = BindResultUtil.resolveError(bindingResult);
        if (!Objects.isNull(errorMessage)) {
            throw new JoinBizException(errorMessage);
        }
        UserEntity userEntity = userService.saveUser(userDTO.getUserEntity());
        return null;
    }
}
