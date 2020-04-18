package com.matrix.join.util;

/**
 * @ClassName ByteUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/29 22:05
 * @Version 1.0
 */
public class DataTypeUtils {

    /**
     * byte -> int
     * @param num
     * @return
     */
    public static Integer convertByteToInteger(Byte num) {
        if (num == null) {
            return null;
        } else {
            return num.intValue();
        }
    }

    /**
     * int -> byte
     * @param num
     * @return
     */
    public static Byte convertIntegerToByte(Integer num) {
        if (num == null) {
            return null;
        } else {
            return num.byteValue();
        }
    }
}
