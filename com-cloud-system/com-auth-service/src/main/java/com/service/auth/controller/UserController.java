package com.service.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

/**
 * @author xiaoMing
 * Create on 2020-08-19.
 */
@RestController
@AllArgsConstructor
public class UserController {

    private final KeyPair keyPair;

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

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @GetMapping("/users/me")
    public Object getCurrentUserInfo(@AuthenticationPrincipal Object principal) {
        return Collections.singletonMap("username", principal);
    }

}
