package com.matrix.join.controller;

import com.auth0.jwt.JWT;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.JoinBizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName BaseController
 * @Description 基本控制器
 * @Author Administrator
 * @Date 2020/2/28 9:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Value("${file.head.path}")
    String path;

    @PostMapping("/upload")
    public ApiResponse<String> uploadIcon(@RequestParam(name = "file", required = true) MultipartFile file,
                                          HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWT.decode(token).getAudience().get(0);
        String filename = file.getOriginalFilename();
        if (!filename.matches(BasicConstant.PIC_PATTERN) || file.getSize() == 0) {
            throw new JoinBizException("请输入正确格式文件");
        }
        try {
            File upFile = new File(path + id);
            if (!upFile.exists()) {
                upFile.mkdir();
            }
            file.transferTo(new File(upFile, filename));
            return new ApiResponse<String>().builder().code(200).message("ok").data(id + File.separator + filename).build();
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse<String>().builder().code(200).message("ok").data("上传失败").build();
        }
    }
}
