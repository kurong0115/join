package com.matrix.join.common;

/**
 * @ClassName CommonEnum
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/10 10:57
 * @Version 1.0
 */
public enum CommonEnum {

    /**
     *
     */
    ON(0, "启用"), OFF(1, "停用");

    private final Integer code;

    private final String message;

    private CommonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取编码
     * @param message
     * @return
     */
    public static Integer code(String message) {
        CommonEnum[] enums = CommonEnum.values();
        for (CommonEnum commonEnum: enums) {
            if (commonEnum.message.equals(message)) {
                return commonEnum.code;
            }
        }
        return null;
    }

    /**
     * 获取描述信息
     * @param code
     * @return
     */
    public static String message(Integer code) {
        CommonEnum[] enums = CommonEnum.values();
        for (CommonEnum commonEnum: enums) {
            if (commonEnum.code.equals(code)) {
                return commonEnum.message;
            }
        }
        return null;
    }
}
