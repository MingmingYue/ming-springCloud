package com.service.user.client;

import com.core.model.auth.AuthModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/auth/details/{authId}")
    AuthModel getAuth(@PathVariable("authId") Integer id);
}
