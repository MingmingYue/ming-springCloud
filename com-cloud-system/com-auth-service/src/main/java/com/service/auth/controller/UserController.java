package com.service.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author xiaoMing
 * Create on 2020-08-19.
 */
@RestController
public class UserController {

    @GetMapping("/")
    public String index(Authentication authentication) throws IOException {
        return "登录成功";
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/user/info")
    public Object user(Authentication authentication) throws IOException {
        return authentication.getPrincipal();
    }
}
