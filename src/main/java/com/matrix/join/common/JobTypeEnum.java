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
    NO_REQUIREMENT(0, "无要求"), FULL_TIME_JOB(1, "全职"), PART_TIME_JOB(2, "兼职");

    private final Integer code;

    private final String type;

    JobTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
