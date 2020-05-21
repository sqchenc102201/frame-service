package com.it.frame.common.annotation;

import com.it.frame.common.enums.PermissionEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 权限定义接口
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    // 检查项枚举
    PermissionEnum type() default PermissionEnum.DEFAULT;
}
