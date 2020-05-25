package com.it.frame.common.exception;

import com.it.frame.vo.common.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一异常拦截
 *
 * @author chenshaoqi
 * @since 2020/05/14
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvisor {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public ResultVO handle(Exception e) {
        // 自定义异常编码 + 自定义统一提醒
        if (e instanceof CustomException) {
            CustomException exception = (CustomException) e;
            Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            if (exception.getCode() != null && exception.getCode() != 0) {
                code = exception.getCode();
            }
            log.warn(exception.getMsg(), e);
            return new ResultVO<>(code, exception.getMsg());
        }
        // 异常编码 + 统一提醒
        log.error("application error:", e);
        return new ResultVO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "application error");
    }

}
