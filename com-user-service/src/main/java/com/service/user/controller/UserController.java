package com.service.user.controller;

import com.service.user.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoMing
 * Create on 2020-08-04.
 */
@Slf4j
@RestController
public class UserController {

    @GetMapping(value = "/details/{userId}")
    public UserModel accept(@PathVariable(value = "userId") Integer userId) {
        return UserModel.builder().userId(userId).username("Ming").build();
    }
}
