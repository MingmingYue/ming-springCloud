package com.service.api.hystrix;

import com.service.api.UserFeignApi;
import com.service.api.model.AuthPermission;
import com.service.api.model.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Slf4j
@Component
public class UserFeignApiHystrix implements UserFeignApi {

    @Override
    public AuthUser findUserByUsername(String username) {
        log.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }

    @Override
    public AuthUser findUserByMobile(String mobile) {
        log.error("调用{}异常:{}", "findUserByMobile", mobile);
        return null;
    }

    @Override
    public Set<AuthPermission> findMenuByRole(String roleCode) {
        log.error("调用{}异常:{}", "findMenuByRole", roleCode);
        return null;
    }
}
