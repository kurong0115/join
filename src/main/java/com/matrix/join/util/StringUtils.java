package com.matrix.join.util;

import java.util.Arrays;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/27 20:38
 * @Version 1.0
 */
public class StringUtils {

    /**
     * 连接不定参数的String
     * @param param
     * @return
     */
    public static String concat(String...param) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(param).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
