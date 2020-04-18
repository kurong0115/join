package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName SalaryEnum
 * @Description 工资枚举
 * @Author Administrator
 * @Date 2019/12/24 14:15
 * @Version 1.0
 */
public enum SalaryEnum {
    /**
     * 不限
     */
    SALARY_A(0, "不限"),

    /**
     * 3K以下
     */
    SALARY_B(1, "3K以下"),

    /**
     * 3-5K
     */
    SALARY_C(2, "3-5K"),

    /**
     * 5-10K
     */
    SALARY_D(3, "5-10K"),

    /**
     * 10-15K
     */
    SALARY_E(4, "10-15K"),

    /**
     * 15-20K
     */
    SALARY_F(5, "15-20K"),

    /**
     * 20-30K
     */
    SALARY_G(6, "20-30K"),

    /**
     * 30-50K
     */
    SALARY_H(7, "30-50K"),

    /**
     * 50K以上
     */
    SALARY_I(8, "50K以上");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 工资情况
     */
    private String salary;

    /**
     * 私有构造方法
     * @param code
     * @param salary
     */
    private SalaryEnum(Integer code, String salary) {
        this.code = code;
        this.salary = salary;
    }

    /**
     * 根据编码获取薪资水平
     * @param code
     * @return
     */
    public static String salary(Integer code) {
        if (!Objects.isNull(code)) {
            SalaryEnum[] enums = SalaryEnum.values();
            String salary = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.salary).collect(Collectors.toList());
            return salary = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据薪资水平获取编码
     * @param salary
     * @return
     */
    public static Integer code(String salary) {
        if (!Objects.isNull(salary)) {
            SalaryEnum[] enums = SalaryEnum.values();
            Integer code = null;
            List<Integer> list = Arrays.stream(enums).filter(x -> salary.equals(x.salary)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(SalaryEnum.values()).forEach(x -> System.out.print(x.code + "为" + x.salary + "，"));
    }
}
