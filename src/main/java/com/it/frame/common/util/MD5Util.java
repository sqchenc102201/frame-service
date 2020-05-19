package com.it.frame.common.util;


import org.springframework.util.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 获取MD5码工具类
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
public class MD5Util {

    private static String getFileMD5(String path) {
        String fileCode = "";
        try {
            fileCode = DigestUtils.md5DigestAsHex(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileCode;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getFileMD5("C:\\software\\JetbrainsCrack-release-enc.zip"));
    }
}
