package com.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiaoMing
 * Create on 2020-08-05.
 */
@EnableFeignClients
@SpringCloudApplication
@ComponentScan(basePackages = {"com.service.api","com.service.auth", "com.service","com.core.config"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
