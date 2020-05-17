package com.it.frame.po.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("frame_test_user")
public class TestPO {
    private Long id;
    private String account;
    private String name;
    private Integer sex;
    private Integer age;

}
