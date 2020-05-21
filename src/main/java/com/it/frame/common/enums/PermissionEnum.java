package com.it.frame.common.enums;

/**
 * 权限码定义枚举类
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
public enum PermissionEnum {
    DEFAULT("com.frame.default"),
    TEST("com.frame.test"),
    TEST_ADD("com.frame.test.add");

    PermissionEnum(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
