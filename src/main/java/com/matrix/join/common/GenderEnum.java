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
        GenderEnum[] enums = GenderEnum.values();
        for (GenderEnum genderEnum: enums) {
            if (genderEnum.code.equals(code)) {
                return genderEnum.gender;
            }
        }
        return null;
    }

    /**
     * 获取编码
     * @param gender
     * @return
     */
    public static Integer code(String gender) {
        GenderEnum[] enums = GenderEnum.values();
        for (GenderEnum genderEnum: enums) {
            if (genderEnum.gender.equals(gender)) {
                return genderEnum.code;
            }
        }
        return null;
    }
}
