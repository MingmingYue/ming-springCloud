package com.service.auth.controller;

import com.core.commons.R;
import com.core.commons.constants.SecurityConstant;
import com.core.model.auth.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Slf4j
@RestController
public class AuthController {

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstant.TOKEN_USER_DETAIL, key = "#accesstoken")
    public R<Boolean> removeToken(String accesstoken) {
        boolean isRemoved = consumerTokenServices.revokeToken(accesstoken);
        return new R<Boolean>().data(isRemoved);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("ftl/login");
    }

    @GetMapping(value = "/details/{authId}")
    public AuthModel accept(@PathVariable(value = "authId") Integer authId) {
        System.out.println("==================== authId: " + authId);
        return AuthModel.builder().authId(authId).authName("AuthId").build();
    }
}
