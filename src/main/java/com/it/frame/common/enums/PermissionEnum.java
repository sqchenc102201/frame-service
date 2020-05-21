package com.it.frame.common.enums;

/**
 * 权限码定义枚举类
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
public enum PermissionEnum {
    DEFAULT("com.it.frame.default"),
    TEST("com.it.frame.test"),
    TEST_ADD("com.it.frame.test.add"),
    TEST_EDIT("com.it.frame.test.edit"),
    FILE_UPLOAD("com.it.frame.file.upload");

    PermissionEnum(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
