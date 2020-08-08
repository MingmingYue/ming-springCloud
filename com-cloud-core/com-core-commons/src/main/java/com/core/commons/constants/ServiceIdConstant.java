package com.core.commons.constants;

/**
 * @author xiaoMing
 * Create on 2020-08-02.
 */
public interface ServiceIdConstant {

    /**
     * 认证服务的SERVICEID（zuul 配置的对应）
     */
    String AUTH_SERVICE = "auth-server";

    /**
     * ADMIN模块
     */
    String ADMIN_SERVICE = "user-service";

    /**
     * 所有模块
     */
    String ALL_SERVICE = "*";
}
