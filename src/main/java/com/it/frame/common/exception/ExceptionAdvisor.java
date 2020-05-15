package com.it.common.exception;

import com.it.common.vo.JsonVO;
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
@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public JsonVO handle(Exception e) {
        // 自定义异常编码 + 自定义统一提醒
        if (e instanceof FrameException) {
            log.warn(e.getMessage(), e);
            FrameException exception = (FrameException) e;
            Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            if (exception.getCode() != null && exception.getCode() != 0) {
                code = exception.getCode();
            }
            return new JsonVO<>(code, exception.getMsg());
        }
        // 异常编码 + 统一提醒
        log.error("application error:", e);
        return new JsonVO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "application error");
    }

}
