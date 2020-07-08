package com.matrix.join.util;

import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName PrimaryKeyGenerator
 * @Description 主键生成类
 * @Author Administrator
 * @Date 2019/12/24 16:36
 * @Version 1.0
 */
public class PrimaryKeyGenerator {

    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 以当前时间戳生成唯一编号
     * @return
     */
    public static BigInteger generatePrimaryKey() {
        BigInteger bigInteger = null;
        try {
            lock.lock();
            Thread.sleep(1);
            bigInteger = BigInteger.valueOf(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return bigInteger;
    }

    public static void main(String[] args) {
        System.out.println(generatePrimaryKey());
    }
}
