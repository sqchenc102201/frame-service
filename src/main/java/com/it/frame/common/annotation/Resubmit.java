package com.it.frame.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 避免重复提交注解定义
 *
 * @author chenshaoqi
 * @since 2020/5/25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resubmit {
    /**
     * 指定时间内不可重复提交,单位毫秒
     *
     * @return 毫秒
     */
    long timeout() default 1000;


}
