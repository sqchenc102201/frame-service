package com.it.frame.po.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("frame_test_user")
public class TestPO implements Serializable {
    private Long id;
    private String account;
    private String name;
    private Integer sex;
    private Integer age;

}
