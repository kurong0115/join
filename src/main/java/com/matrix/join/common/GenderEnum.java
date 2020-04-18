package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName GenderEnum
 * @Description 性别枚举
 * @Author Administrator
 * @Date 2020/1/31 15:07
 * @Version 1.0
 */
public enum GenderEnum {

    /**
     *
     */
    NO_REQUIREMENT(0, "不限"), MALE(1, "男"), FEMALE(2, "女");

    private final Integer code;

    private final String gender;

    GenderEnum(Integer code, String gender) {
        this.code = code;
        this.gender = gender;
    }

    /**
     * 获取描述
     * @param code
     * @return
     */
    public static String gender(Integer code) {
        if (!Objects.isNull(code)) {
            GenderEnum[] enums = GenderEnum.values();
            String gender = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.gender).collect(Collectors.toList());
            return gender = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取编码
     * @param gender
     * @return
     */
    public static Integer code(String gender) {
        if (!Objects.isNull(gender)) {
            GenderEnum[] enums = GenderEnum.values();
            Integer code = null;
            List<Integer> list = Arrays.stream(enums).filter(x -> gender.equals(x.gender)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(GenderEnum.values()).forEach(x -> System.out.print(x.code + "为" + x.gender + "，"));
    }
}
