//package com.service.gateway.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * @author xiaoMing
// * Create on 2020-08-30.
// */
//@Configuration
//public class ResourceServerConfig {
//
//    @Configuration
//    @EnableResourceServer
//    public class UAAServerConfig extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//            resources
//                    //设置我这个resource的id
//                    .resourceId("user-service")
//                    //这个貌似是配置要不要把token信息记录在session中
//                    .stateless(true);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/user/**").access("#oauth2.hasScope('server')")
//                    .anyRequest().authenticated();
//        }
//    }
//}
