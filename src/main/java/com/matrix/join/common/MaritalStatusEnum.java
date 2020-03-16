package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MaritalStatusEnum
 * @Description 婚姻状况
 * @Author Administrator
 * @Date 2020/3/16 21:26
 * @Version 1.0
 */
public enum MaritalStatusEnum {

    /**
     *
     */
    UNMARRIED(0, "未婚"), MARRIED(1, "已婚");

    private Integer code;

    private String message;

    MaritalStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取编码
     * @param message
     * @return
     */
    public static Integer code(String message) {
        MaritalStatusEnum[] enums = MaritalStatusEnum.values();
        Integer code = null;
        List<Integer> list = Arrays.stream(enums).filter(x -> message.equals(x.message)).map(x -> x.code).collect(Collectors.toList());
        return code = list.isEmpty() ? null : list.get(0);
    }

    /**
     * 获取描述信息
     * @param code
     * @return
     */
    public static String message(Integer code) {
        MaritalStatusEnum[] enums = MaritalStatusEnum.values();
        String message = null;
        List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.message).collect(Collectors.toList());
        return message = list.isEmpty() ? null : list.get(0);
    }
}
