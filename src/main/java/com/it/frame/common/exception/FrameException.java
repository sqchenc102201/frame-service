package com.it.frame.common.exception;

import com.it.frame.common.enums.FrameErrorStatus;

/**
 * 自定义异常
 *
 * @author chenshaoqi
 * @since 2020/05/14
 */
public class FrameException extends Exception {

    private Integer code;
    private String msg;

    FrameException(FrameErrorStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
