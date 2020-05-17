package com.it.frame.vo;

/**
 * 统一返回VO
 *
 * @author chenshaoqi
 * @since 2020/5/15
 */
public class ResultVO<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String message;

    // 响应中的数据
    private T data;

    public ResultVO(T data) {
        this.status = 200;
        this.message = "success";
        this.data = data;
    }

    public ResultVO(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public ResultVO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


}
