package com.matrix.join.protocol;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author Administrator
 * @Date 2020/2/12 11:25
 * @Version 1.0
 */
//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse<Object> resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return null;
    }
}
