package com.service.auth.controller;

import com.core.model.auth.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Slf4j
@RestController
public class AuthController {

    @GetMapping(value = "/details/{authId}")
    public AuthModel accept(@PathVariable(value = "authId") Integer authId) {
        System.out.println("==================== authId: " + authId);
        return AuthModel.builder().authId(authId).authName("AuthId").build();
    }
}
