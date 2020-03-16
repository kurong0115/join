package com.matrix.join.constant;

import java.math.BigInteger;

/**
 * @ClassName BasicConstant
 * @Description 基础常量
 * @Author Administrator
 * @Date 2020/1/31 11:37
 * @Version 1.0
 */
public class BasicConstant {

    /**
     * 日期图腾
     */
    public static final String DATA_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 图片
     */
    public static final String PIC_PATTERN = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.pdf|.PDF)$";

    /**
     * 最长密码
     */
    public static final int MAX_PASSWORD_LENGTH = 32;

    /**
     * 默认公司编号
     */
    public static final BigInteger DEFAULT_COMPANY_NO = new BigInteger("123");

}
