package com.it.frame.service.common.impl;

import com.it.frame.common.annotation.Permission;
import com.it.frame.service.common.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限实现类
 *
 * @author chenshaoqi
 * @since 2020/5/21
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean checkProcess(Permission permission, HttpServletRequest request, HttpServletResponse response) {
        log.info("测试权限验证过程" + permission.type().getCode());
        return Boolean.TRUE;
    }
}
