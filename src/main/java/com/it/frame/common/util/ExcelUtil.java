package com.it.frame.common.util;

import com.alibaba.excel.EasyExcel;

import java.io.InputStream;
import java.util.List;

/**
 * easyexcel 操作Excel（读取、写入）
 *
 * @author chenshaoqi
 * @since 2020/5/20
 */
public class ExcelUtil {

    /**
     * 同步读取Excel
     *
     * @param inputStream Excel输入流
     * @param clazz       转换的对象
     * @return 列表
     */
    public static List<?> doReadSync(InputStream inputStream, Class<?> clazz) {
        return EasyExcel.read(inputStream).head(clazz).sheet().doReadSync();
    }

}
