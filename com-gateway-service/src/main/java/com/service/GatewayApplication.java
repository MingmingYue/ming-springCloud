package com.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author xiaoMing
 * Create on 2020-08-19.
 */
//@EnableFeignClients
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@ComponentScan(basePackages = {"com.service", "com.core.config"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }
}