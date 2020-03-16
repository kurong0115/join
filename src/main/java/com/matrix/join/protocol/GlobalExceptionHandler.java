package com.matrix.join.protocol;

import com.matrix.join.util.BindResultUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author Administrator
 * @Date 2020/2/12 11:25
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 忽略参数异常处理器
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<String> missingRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return new ApiResponse<String>().builder().code(500).message("failed").data("请求参数 " + e.getParameterName() + " 不能为空").build();
    }

    /**
     * 缺少请求体异常处理器
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<String> missingRequestBodyExceptionHandler(HttpMessageNotReadableException e) {
        return new ApiResponse<String>().builder().code(500).message("failed").data("请求体不能为空").build();
    }

    /**
     * 参数校验异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> resolveException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = BindResultUtil.resolveError(bindingResult);
        return new ApiResponse<String>().builder().code(500).message("failed").data(message).build();
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(JoinBizException.class)
    public ApiResponse<String> bizException(JoinBizException e) {
        String message = e.getMessage();
        return new ApiResponse<String>().builder().code(500).message("failed").data(message).build();
    }

    /**
     * 普通异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> exception(Exception e) {
        return new ApiResponse<String>().builder().code(500).message("failed").data("服务器异常").build();
    }
}
