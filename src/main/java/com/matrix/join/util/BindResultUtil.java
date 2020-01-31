package com.matrix.join.util;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

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
    public static List<String> resolveError(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(x -> {
                errorList.add(x.getDefaultMessage());
            });
        }
        return errorList;
    }
}
