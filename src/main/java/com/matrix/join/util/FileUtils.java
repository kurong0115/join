package com.matrix.join.util;

import java.io.File;

/**
 * @ClassName FileUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/27 21:46
 * @Version 1.0
 */
public class FileUtils {

    /**
     * 删除指定路径的文件
     * @param path
     */
    public static void removeFile(String path) {
        File file = new File(path);
        file.delete();
    }

    /**
     * 重新修改文件名
     * @param fileName
     * @return
     */
    public static String changeFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index <= 0) {
            return null;
        } else {
            return StringUtils.concat(fileName.substring(0, index), SecretUtils.generateCode(), fileName.substring(index));
        }
    }

}
