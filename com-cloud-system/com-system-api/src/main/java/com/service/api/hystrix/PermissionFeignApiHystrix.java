package com.service.api.hystrix;

import com.service.api.PermissionFeignApi;
import com.service.api.model.AuthPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Slf4j
@Service
public class PermissionFeignApiHystrix implements PermissionFeignApi {

    @Override
    public Set<AuthPermission> findMenuByRole(String roleCode) {
        log.error("调用{}异常:{}", "findMenuByRole", roleCode);
        return null;
    }
}