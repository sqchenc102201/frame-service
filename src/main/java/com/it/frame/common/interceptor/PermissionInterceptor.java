package com.it.frame.common.interceptor;

import com.it.frame.common.annotation.Permission;
import com.it.frame.service.common.PermissionService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限校验拦截器
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
public class PermissionInterceptor implements HandlerInterceptor {

    private PermissionService permissionService;

    public PermissionInterceptor(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return Boolean.TRUE;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查方法上的权限
        if (method.isAnnotationPresent(Permission.class)) {
            Permission permission = method.getAnnotation(Permission.class);
            return permissionService.checkProcess(permission, request, response);
        }
        // 检查类权限
        Class<?> clazz = handlerMethod.getBeanType();
        if (clazz.isAnnotationPresent(Permission.class)) {
            Permission permission = clazz.getAnnotation(Permission.class);
            return permissionService.checkProcess(permission, request, response);
        }
        return Boolean.TRUE;
    }
}
