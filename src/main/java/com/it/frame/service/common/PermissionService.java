package com.it.frame.service.common;

import com.it.frame.common.annotation.Permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限接口类
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
public interface PermissionService {

    /**
     * 权限校验过程接口
     * @param permission 权限码
     * @param request request
     * @param response response
     * @return true：通过 false：不通过
     */
    boolean checkProcess(Permission permission, HttpServletRequest request, HttpServletResponse response);
}
