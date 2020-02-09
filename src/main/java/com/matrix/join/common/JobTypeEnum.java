package com.matrix.join.common;

/**
 * @ClassName JobTypeEnum
 * @Description 职位类型枚举
 * @Author Administrator
 * @Date 2020/1/31 15:46
 * @Version 1.0
 */
public enum JobTypeEnum {

    /**
     *
     */
    NO_REQUIREMENT(0, "不限"), FULL_TIME_JOB(1, "全职"), PART_TIME_JOB(2, "兼职");

    private final Integer code;

    private final String type;

    JobTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    /**
     * 获取描述
     * @param code
     * @return
     */
    public static String type(Integer code) {
        JobTypeEnum[] enums = JobTypeEnum.values();
        for (JobTypeEnum jobTypeEnum: enums) {
            if (jobTypeEnum.code.equals(code)) {
                return jobTypeEnum.type;
            }
        }
        return null;
    }

    /**
     * 获取编码
     * @param type
     * @return
     */
    public static Integer code(String type) {
        JobTypeEnum[] enums = JobTypeEnum.values();
        for (JobTypeEnum jobTypeEnum: enums) {
            if (jobTypeEnum.type.equals(type)) {
                return jobTypeEnum.code;
            }
        }
        return null;
    }
}
