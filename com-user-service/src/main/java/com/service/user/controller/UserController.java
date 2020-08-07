package com.service.user.controller;

import com.core.model.user.UserModel;
import com.service.user.client.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author xiaoMing
 * Create on 2020-08-04.
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private AuthClient authClient;

    @GetMapping(value = "/details/{userId}")
    public UserModel accept(@PathVariable(value = "userId") Integer userId) {
        Integer randId = new Random().nextInt(100);
        System.out.println("==================== userId: " + randId);
        System.out.println(authClient.getAuth(randId));
        return UserModel.builder().userId(userId).username("Ming").build();
    }
}
