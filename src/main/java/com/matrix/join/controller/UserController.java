package com.matrix.join.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.constant.UserConstant;
import com.matrix.join.dto.CompanyUserDTO;
import com.matrix.join.dto.UserDTO;
import com.matrix.join.dto.UserInfoDTO;
import com.matrix.join.entity.CompanyEntity;
import com.matrix.join.entity.UserEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.Pagination;
import com.matrix.join.service.CompanyService;
import com.matrix.join.service.MailService;
import com.matrix.join.service.UserService;
import com.matrix.join.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/31 16:22
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    MailService mailService;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public ApiResponse<UserInfoDTO> login(@RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "password", required = true) String password,
                                          @RequestParam(value = "type", required = true) Byte userType) {
        UserEntity userEntity = userService.login(email, password, userType);
        UserInfoDTO infoDTO = UserInfoDTO.builder().user(userEntity).token(JwtUtils.getToken(userEntity)).build();
        return new ApiResponse<UserInfoDTO>().builder().code(200).message("ok").data(infoDTO).build();
    }

    @UserLogin(userType = UserConstant.ADMIN)
    @GetMapping(value = "/listUser")
    public ApiResponse<List<UserDTO>> listUser(@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "9", required = false) int pageSize,
                                               @RequestParam(value = "isDel", required = false, defaultValue = "3") byte isDel,
                                               @RequestParam(value = "name", required = false) String name) {
        IPage<UserEntity> page = userService.listUser(name, isDel, new Page<UserEntity>(pageNum, pageSize));
        return new ApiResponse<List<UserDTO>>().builder().code(200).message("ok")
                .data(page.getRecords().stream().map(x -> new UserDTO().setUserEntity(x)).collect(Collectors.toList()))
                .pagination(Pagination.convertPage(page)).build();
    }

    @GetMapping(value = "/getUserInfoById")
    public ApiResponse<UserDTO> get(@RequestParam(value = "userId", required = true) BigInteger userId) {
        UserEntity userEntity = userService.getUserByUserId(userId);
        return new ApiResponse<UserDTO>().builder().code(200).message("ok").data(new UserDTO().setUserEntity(userEntity)).build();
    }

    @GetMapping(value = "/getUserInfoByToken")
    public ApiResponse<CompanyUserDTO> getUserByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWT.decode(token).getAudience().get(0);
        UserEntity userEntity = userService.getUserByUserId(BigInteger.valueOf(Long.decode(id)));
        CompanyEntity company = companyService.getCompanyByNo(userEntity.getCompanyNo());
        return new ApiResponse<CompanyUserDTO>().builder().code(200).message("ok")
                .data(new CompanyUserDTO().setUser(userEntity).setCompany(company)).build();
    }

    @PostMapping(value = "/register")
    public ApiResponse<UserEntity> register(@Valid @RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.saveUser(userDTO.getUserEntity(), userDTO.getVerification());
        return new ApiResponse<UserEntity>().builder().code(200).message("ok").data(userEntity).build();
    }

    @UserLogin
    @PostMapping(value = "/updateUserInfo")
    public ApiResponse<CompanyUserDTO> updateUserInfo(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.updateUserInfo(userDTO.getUserEntity());
        CompanyEntity company = companyService.getCompanyByNo(userEntity.getCompanyNo());
        return new ApiResponse<CompanyUserDTO>().builder().code(200).message("ok")
                .data(new CompanyUserDTO().setUser(userEntity).setCompany(company)).build();
    }

    @UserLogin
    @PostMapping(value = "/bindCompany")
    public ApiResponse<String> bindCompany(@RequestParam(value = "companyNo", required = true) BigInteger companyNo,
                                           @RequestParam(value = "userId", required = true) BigInteger userId) {
        int result = userService.bindCompany(userId, companyNo);
        return ApiResponse.responseData(null);
    }

    @PostMapping(value = "/recoveryPassword")
    public ApiResponse<Object> recoveryPassword(@RequestParam(name = "email", required = true) String email,
                                                @RequestParam(name = "secret", required = true) String secret) {
        userService.recoveryPassword(email, secret);
        return ApiResponse.responseData(null);
    }

    @UserLogin
    @PostMapping(value = "/unbound")
    public ApiResponse<Object> unbound(@RequestParam(name = "userId", required = true) BigInteger userId) {
        userService.unbound(userId);
        return ApiResponse.responseData(null);
    }

    @UserLogin
    @PostMapping(value = "/updatePassword")
    public ApiResponse<Object> updatePassword(@RequestParam(name = "userId", required = true) BigInteger userId,
                                              @RequestParam(name = "password", required = true) String password,
                                              @RequestParam(name = "oldPassword", required = true) String oldPassword) {
        userService.updatePassword(userId, password, oldPassword);
        return ApiResponse.responseData(null);
    }

    @UserLogin(userType = UserConstant.ADMIN)
    @GetMapping(value = "stopUser")
    public ApiResponse<Object> stopUser(@RequestParam(name = "userId") BigInteger userId,
                                        @RequestParam(name = "isDel") Byte isDel) {
        userService.stopUser(userId, isDel);
        return ApiResponse.responseData(null);
    }

}
