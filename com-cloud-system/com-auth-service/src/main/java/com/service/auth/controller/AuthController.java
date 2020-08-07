package com.service.auth.controller;

import com.core.model.auth.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Slf4j
@RestController
public class AuthController {

    @GetMapping(value = "/details/{authId}")
    public AuthModel accept(@PathVariable(value = "authId") Integer authId) {
        System.out.println("====================" + authId + new Random().nextInt(100));
        return AuthModel.builder().authId(authId).authName("AuthId").build();
    }
}
