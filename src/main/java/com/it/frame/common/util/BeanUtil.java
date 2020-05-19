package com.it.frame.common.util;

import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * Bean对象处理工具类
 *
 * @author chenshaoqi
 * @since 2020/5/15
 */
public final class BeanUtil extends BeanUtils {
    /**
     * 通过无参数实例化目标对象和复制属性，将POJO对象转换成相应的对象
     *
     * @param source 原对象
     * @param type   目标类型
     * @return 转换后的对象
     */
    public static <T> T convert(Object source, Class<T> type) {
        if (Objects.isNull(source)) {
            try {
                return type.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        T target = instantiateClass(type);
        copyProperties(source, target);
        return target;
    }
}
