package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName MaritalStatusEnum
 * @Description 婚姻状况
 * @Author Administrator
 * @Date 2020/3/16 21:26
 * @Version 1.0
 */
public enum MaritalStatusEnum {

    /**
     *
     */
    UNMARRIED((byte) 0, "未婚"), MARRIED((byte) 1, "已婚");

    private Byte code;

    private String maritalStatus;

    MaritalStatusEnum(Byte code, String maritalStatus) {
        this.code = code;
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取编码
     * @param maritalStatus
     * @return
     */
    public static Byte code(String maritalStatus) {
        if (!Objects.isNull(maritalStatus)) {
            MaritalStatusEnum[] enums = MaritalStatusEnum.values();
            Byte code = null;
            List<Byte> list = Arrays.stream(enums).filter(x -> maritalStatus.equals(x.maritalStatus)).map(x -> x.code).collect(Collectors.toList());
            return code = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取描述信息
     * @param code
     * @return
     */
    public static String maritalStatus(Byte code) {
        if (!Objects.isNull(code)) {
            MaritalStatusEnum[] enums = MaritalStatusEnum.values();
            String maritalStatus = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.maritalStatus).collect(Collectors.toList());
            return maritalStatus = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("1"));
    }
}
