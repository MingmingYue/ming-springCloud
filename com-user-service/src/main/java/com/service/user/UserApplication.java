package com.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiaoMing
 * Create on 2020-08-03.
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.service", "com.core.register"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
