package com.it.frame.common.util;

import com.it.frame.common.annotation.Permission;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 注解工具类
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
public final class AnnotationUtil {
    /**
     * 获取指定文件下面的Permission注解的类和方法的所有code
     *
     * @param packageName 扫描的文件：eg：com.it.frame
     * @return 注解的类和方法的所有code
     */
    public static Set<String> findPermissionAllCode(String packageName) {
        Set<String> codeList = new HashSet<>();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(Permission.class);
        for (Class clazz : classList) {
            // 检查类上的注解
            if (clazz.isAnnotationPresent(Permission.class)) {
                Permission clazzPermission = (Permission) clazz.getAnnotation(Permission.class);
                codeList.add(clazzPermission.type().getCode());
            }
            // 检查方法上的注解
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Permission.class)) {
                    Permission methodPermission = method.getAnnotation(Permission.class);
                    codeList.add(methodPermission.type().getCode());
                }
            }
        }
        return codeList;
    }

    public static void main(String[] args) {
        AnnotationUtil.findPermissionAllCode("com.it.frame").forEach(System.out::println);
    }
}
