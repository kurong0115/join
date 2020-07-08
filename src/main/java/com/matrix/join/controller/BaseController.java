package com.matrix.join.controller;

import com.auth0.jwt.JWT;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.constant.BasicConstant;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.JoinBizException;
import com.matrix.join.service.CompanyService;
import com.matrix.join.service.DeliveryRecordService;
import com.matrix.join.service.JobService;
import com.matrix.join.service.UserService;
import com.matrix.join.util.FileUtils;
import com.matrix.join.util.StringUtils;
import com.matrix.join.vo.CountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
@SuppressWarnings("all")
@RestController
@RequestMapping("/base")
public class BaseController {

    @Value("${file.head.path}")
    String path;

    @Value("${file.head.file-path}")
    String filePath;

    @Value("${file.head.project-path}")
    String projectPath;

    private CompanyService companyService;

    private JobService jobService;

    private UserService userService;

    private DeliveryRecordService recordService;

    @Autowired
    public BaseController(CompanyService companyService, JobService jobService, UserService userService, DeliveryRecordService recordService) {
        this.companyService = companyService;
        this.jobService = jobService;
        this.userService = userService;
        this.recordService = recordService;
    }

    @UserLogin
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
            /*File upFile = new File(StringUtils.concat(path, id, File.separator, filename));*/
            File upFile = new File(StringUtils.concat(path, id));
            if (!upFile.exists()) {
                upFile.mkdir();
            }
            upFile = new File(StringUtils.concat(path, id, BasicConstant.SEPARATOR, filename));
            if (upFile.exists()) {
                filename = FileUtils.changeFileName(filename);
                upFile = new File(StringUtils.concat(path, id, BasicConstant.SEPARATOR, filename));
            }
            file.transferTo(upFile);
            return new ApiResponse<String>().builder().code(200).message("ok").data(StringUtils.concat(filePath, id, BasicConstant.SEPARATOR, filename)).build();
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse<String>().builder().code(500).message("fail").data("上传失败").build();
        }
    }

    @GetMapping(value = "getCount")
    public ApiResponse<CountVO> getCount() {
        return ApiResponse.responseData(new CountVO(companyService.count(), userService.count(), jobService.count(), recordService.count()));
    }

}
