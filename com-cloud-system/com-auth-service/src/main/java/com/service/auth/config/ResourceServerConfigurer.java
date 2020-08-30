package com.service.auth.config;

/**
 * Created by Steven on 2020/6/12.
 */

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(500))
                .and()
                // 其余所有请求全部需要鉴权认证
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/details/**").access("#oauth2.hasScope('server')")
                .anyRequest().authenticated();
    }
}
