package com.matrix.join.common;

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
    NO_REQUIREMENT(0, "无要求"), MALE(1, "男"), FEMALE(2, "女");

    private final Integer code;

    private final String gender;

    GenderEnum(Integer code, String gender) {
        this.code = code;
        this.gender = gender;
    }
}
