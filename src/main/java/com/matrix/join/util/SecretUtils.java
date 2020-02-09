package com.matrix.join.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName SecretUtils
 * @Description 用户密码加密工具类
 * @Author Administrator
 * @Date 2020/1/31 16:07
 * @Version 1.0
 */
public class SecretUtils {

    /**
     * md5加密
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        MessageDigest md5;
        try {
            md5= MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            return new BigInteger(1,digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**.
     * 生成六位随机数字验证码
     * @return
     */
    public static String generateCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    public static void main(String[] args) {
        System.out.println(getMD5String("123"));
        System.out.println(generateCode());
    }
}
