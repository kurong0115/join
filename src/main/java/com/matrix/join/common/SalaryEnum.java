package com.matrix.join.common;

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
    SALARY_A(1, "不限"),

    /**
     * 3K以下
     */
    SALARY_B(2, "3K以下"),

    /**
     * 3-5K
     */
    SALARY_C(3, "3-5K"),

    /**
     * 5-10K
     */
    SALARY_D(4, "5-10K"),

    /**
     * 10-15K
     */
    SALARY_E(5, "10-15K"),

    /**
     * 15-20K
     */
    SALARY_F(6, "15-20K"),

    /**
     * 20-30K
     */
    SALARY_G(7, "20-30K"),

    /**
     * 30-50K
     */
    SALARY_H(8, "30-50K"),

    /**
     * 50K以上
     */
    SALARY_I(9, "50K以上");

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
    private SalaryEnum(Integer code, String salary){
        this.code = code;
        this.salary = salary;
    }

    /**
     * 根据编码获取薪资水平
     * @param code
     * @return
     */
    public static String salary(Integer code){
        SalaryEnum[] enums = SalaryEnum.values();
        for (SalaryEnum salaryEnum: enums){
            if (salaryEnum.code.equals(code)){
                return salaryEnum.salary;
            }
        }
        return null;
    }

    /**
     * 根据薪资水平获取编码
     * @param salary
     * @return
     */
    public static Integer code(String salary){
        SalaryEnum[] enums = SalaryEnum.values();
        for (SalaryEnum salaryEnum: enums){
            if (salaryEnum.salary.equals(salary)){
                return salaryEnum.code;
            }
        }
        return null;
    }
}
