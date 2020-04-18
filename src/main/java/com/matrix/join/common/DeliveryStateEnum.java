package com.matrix.join.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName DeliveryStateEnum
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 11:37
 * @Version 1.0
 */
public enum DeliveryStateEnum {

    /**
     * 简历投递状态
     */
    WAITING_FOR_OBSERVATION((byte) 0, "投递成功"), APPROVED((byte) 1, "初筛通过"), REFUSED((byte) 2, "初筛未通过");

    private final Byte code;

    private final String state;

    private DeliveryStateEnum(Byte code, String state) {
        this.code = code;
        this.state = state;
    }

    /**
     * 获取编码
     * @param state
     * @return
     */
    public static Byte code(String state) {
        if (!Objects.isNull(state)) {
            DeliveryStateEnum[] enums = DeliveryStateEnum.values();
            Byte code = null;
            List<Byte> list = Arrays.stream(enums).filter(x -> state.equals(x.state)).map(x -> x.code).collect(Collectors.toList());
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
    public static String state(Byte code) {
        if (!Objects.isNull(code)) {
            DeliveryStateEnum[] enums = DeliveryStateEnum.values();
            String state = null;
            List<String> list = Arrays.stream(enums).filter(x -> code.equals(x.code)).map(x -> x.state).collect(Collectors.toList());
            return state = list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }
}
