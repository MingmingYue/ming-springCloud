package com.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiaoMing
 * Create on 2020-08-19.
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.service", "com.core.config"})
public class GatewayApplication  {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(GatewayApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
//        return new LoadBalancerInterceptor(loadBalance);
//    }
}