package com.service.api;

import com.service.api.hystrix.PermissionFeignApiHystrix;
import com.service.api.model.AuthPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@FeignClient(name = "business-admin-server", fallback = PermissionFeignApiHystrix.class)
public interface PermissionFeignApi {

    /**
     * 通过角色名查询菜单
     */
    @GetMapping(value = "/api/findMenuByRole/{roleCode}")
    Set<AuthPermission> findMenuByRole(@PathVariable("roleCode") String roleCode);
}
