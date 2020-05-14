package com.it.frame.common.enums;

public enum FrameErrorStatus {

    FILE_IO_ERROR(50001, "文件读取或输出异常");

    private Integer code;
    private String msg;

    FrameErrorStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
