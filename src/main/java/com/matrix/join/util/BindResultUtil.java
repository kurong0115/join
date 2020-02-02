package com.matrix.join.util;

import org.springframework.validation.BindingResult;


/**
 * @ClassName BindResultUtil
 * @Description 参数校验工具类
 * @Author Administrator
 * @Date 2020/1/29 13:03
 * @Version 1.0
 */
public class BindResultUtil {

    /**
     * 解析参数
     * @param bindingResult
     * @return
     */
    public static String resolveError(BindingResult bindingResult) {
        String errorMessage = null;
        if (bindingResult.hasErrors()) {
            errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        return errorMessage;
    }
}
