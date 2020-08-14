package com.service.api;

import com.service.api.hystrix.UserFeignApiHystrix;
import com.service.api.model.AuthUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@FeignClient(name = "user-service", fallback = UserFeignApiHystrix.class)
public interface UserFeignApi {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     */
    @GetMapping("/api/findUserByUsername/{username}")
    AuthUser findUserByUsername(@PathVariable("username") String username);

    /**
     * 通过手机号查询用户、角色信息
     */
    @GetMapping("/api/findUserByMobile/{mobile}")
    AuthUser findUserByMobile(@PathVariable("mobile") String mobile);
}
