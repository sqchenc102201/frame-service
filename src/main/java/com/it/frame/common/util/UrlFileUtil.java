package com.it.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * URL下载文件保存文件工具类
 * @author chenshaoqi
 * @since 2020/05/14
 */
@Slf4j
public class UrlFileUtil {

    /**
     * 从指定URL下载文件并保存到指定目录
     *
     * @param url      请求的路径
     * @param filePath 文件将要保存的目录
     * @param fileName 文件将要保存的文件名
     * @param method   请求方法，包括POST和GET
     * @return 文件
     */
    public static File saveUrlFile(String url, String filePath, String fileName, String method) {
        // 创建不同的文件夹目录
        File file = new File(filePath);
        // 判断文件夹是否存在
        if (!file.exists()) {
            // 如果文件夹不存在，则创建新的的文件夹
            boolean mkFlag = file.mkdirs();
            if (!mkFlag) {
                log.error("创建文件下载保存指定目录失败！");
            }
        }
        FileOutputStream fileOut;
        HttpURLConnection conn;
        InputStream inputStream;
        try {
            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            // 以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            // 连接指定的资源
            conn.connect();
            // 获取网络输入流
            inputStream = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
            // 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            // 保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e) {
            log.error("指定URL下载文件并保存到指定目录异常！", e);
        }

        return file;
    }

    public static void main(String[] args) {
        String photoUrl = "https://ttavatar.myoas.com/g1/M00/02/02/rBAp1F2NqiaAbJtuAAIMW7MspYI490.png";   // 文件URL地址
        String fileName = "new.png";     // 为下载的文件命名
        String filePath = "D:\\testdata\\png\\test";      //保存目录
        saveUrlFile(photoUrl, filePath, fileName, "GET");
    }


}
