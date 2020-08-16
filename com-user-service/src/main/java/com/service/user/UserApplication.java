package com.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiaoMing
 * Create on 2020-08-03.
 */
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.service", "com.core"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
