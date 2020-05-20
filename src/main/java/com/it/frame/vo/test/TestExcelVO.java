package com.it.frame.vo.test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/20
 */
@Data
public class TestExcelVO {

    @ExcelProperty("工号")
    private String account;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("成绩")
    private Double score;

    @ExcelProperty("创建时间")
    private Date created;

}
