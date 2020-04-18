package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName EducationEnum
 * @Description 学历要求枚举
 * @Author Administrator
 * @Date 2019/12/24 14:27
 * @Version 1.0
 */
public enum EducationEnum {

    /**
     *
     */
    EDUCATION(0, "不限"),

    /**
     * 初中及以下
     */
    EDUCATION_A(1, "初中"),

    /**
     * 中专/中技
     */
    EDUCATION_B(2, "中专/中技"),

    /**
     * 高中
     */
    EDUCATION_C(3, "高中"),

    /**
     * 大专
     */
    EDUCATION_D(4, "大专"),

    /**
     * 本科
     */
    EDUCATION_E(5, "本科"),

    /**
     * 硕士
     */
    EDUCATION_F(6, "硕士"),

    /**
     * 博士
     */
    EDUCATION_G(7, "博士");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 私有构造方法单例
     * @param code
     * @param education
     */
    private EducationEnum(Integer code, String education) {
        this.code = code;
        this.education = education;
    }

    /**
     * 根据编码获取描述信息
     * @param code
     * @return
     */
    public static String education(Integer code) {
        if (!Objects.isNull(code)) {
            EducationEnum[] enums = EducationEnum.values();
            String education = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.education).collect(Collectors.toList());
            return education = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据薪资水平获取编码
     * @param education
     * @return
     */
    public static Integer code(String education) {
        if (!Objects.isNull(education)) {
            EducationEnum[] enums = EducationEnum.values();
            Integer code = null;
            List<Integer> list = Arrays.stream(enums).filter(x -> education.equals(x.education)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "EducationEnum{" +
                "code=" + code +
                ", education='" + education + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Arrays.stream(EducationEnum.values()).forEach(x -> System.out.print(x.code + "为" + x.education + "，"));
    }
}
