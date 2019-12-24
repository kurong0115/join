package com.matrix.join.common;

/**
 * @ClassName WorkExperienceEnum
 * @Description 工作经验枚举类
 * @Author Administrator
 * @Date 2019/12/24 14:01
 * @Version 1.0
 */
public enum WorkExperienceEnum {
    /**
     * 在校生
     */
    WORK_EXPERIENCE_ENUM_A(1, "在校生"),

    /**
     * 应届生
     */
    WORK_EXPERIENCE_ENUM_B(2, "应届生"),

    /**
     * 1年以内
     */
    WORK_EXPERIENCE_ENUM_C(3, "1年以内"),

    /**
     * 1-3年
     */
    WORK_EXPERIENCE_ENUM_D(4, "1-3年"),

    /**
     * 3-5年
     */
    WORK_EXPERIENCE_ENUM_E(5, "3-5年"),

    /**
     * 5-10年
     */
    WORK_EXPERIENCE_ENUM_F(6, "5-10年"),

    /**
     * 10年以上
     */
    WORK_EXPERIENCE_ENUM_G(7, "10年以上");


    /**
     * 编码
     */
    private Integer code;

    /**
     * 工作经验
     */
    private String workExperience;

    /**
     * 私有构造方法，不允许其他类来实例化一个对象
     * @param code
     * @param workExperience
     */
    private WorkExperienceEnum(Integer code, String workExperience){
        this.code = code;
        this.workExperience = workExperience;
    }

    /**
     * 根据工作经验获取编码
     * @param workExperience
     * @return
     */
    public static Integer code(String workExperience){
        WorkExperienceEnum[] enums = WorkExperienceEnum.values();
        for (WorkExperienceEnum workExperienceEnum: enums){
            if (workExperienceEnum.workExperience.equals(workExperience)){
                return workExperienceEnum.code;
            }
        }
        return null;
    }

    /**
     * 根据编码获取工作经验
     * @param code
     * @return
     */
    public static String workExperience(Integer code){
        WorkExperienceEnum[] enums = WorkExperienceEnum.values();
        for (WorkExperienceEnum workExperienceEnum: enums){
            if (workExperienceEnum.code.equals(code)){
                return workExperienceEnum.workExperience;
            }
        }
        return null;
    }
}