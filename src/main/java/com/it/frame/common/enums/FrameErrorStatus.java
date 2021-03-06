package com.it.frame.common.enums;

/**
 * 自定义错误码
 * HTTP的可以使用HttpStatus
 *
 * @author chenshaoqi
 * @since 2020/05/13
 */
public enum FrameErrorStatus {

    RESUBMIT(10010, "重复提交"),
    TARGET_METHOD(10011, "目标方法执行失败"),
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
