package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName CommonEnum
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/10 10:57
 * @Version 1.0
 */
public enum CommonEnum {

    /**
     *
     */
    ON(0, "启用"), OFF(1, "停用");

    private final Integer code;

    private final String message;

    private CommonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取编码
     * @param message
     * @return
     */
    public static Integer code(String message) {
        if (!Objects.isNull(message)) {
            CommonEnum[] enums = CommonEnum.values();
            Integer code = null;
            List<Integer> list = Arrays.stream(enums).filter(x -> message.equals(x.message)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取描述信息
     * @param code
     * @return
     */
    public static String message(Integer code) {
        if (!Objects.isNull(code)) {
            CommonEnum[] enums = CommonEnum.values();
            String message = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.message).collect(Collectors.toList());
            return message = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }
}
