package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (!Objects.isNull(code)) {
            JobTypeEnum[] enums = JobTypeEnum.values();
            String type = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.type).collect(Collectors.toList());
            return type = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取编码
     * @param type
     * @return
     */
    public static Integer code(String type) {
        if (!Objects.isNull(type)) {
            JobTypeEnum[] enums = JobTypeEnum.values();
            Integer code = null;
            List<Integer> list = Arrays.stream(enums).filter(x -> type.equals(x.type)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }
}
