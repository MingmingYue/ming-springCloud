package com.service.gateway.serivce;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限接口
 *
 * @author xiaoMing
 * Create on 2020-08-12.
 */
public interface PermissionService {

    /**
     * 判断请求是否有权限
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
