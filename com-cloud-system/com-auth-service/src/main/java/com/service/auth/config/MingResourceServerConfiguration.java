package com.service.auth.config;

import com.core.config.configuration.MingUrlsConfiguration;
import com.service.auth.component.ajax.AjaxSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER - 4)
public class MingResourceServerConfiguration extends WebSecurityConfigurerAdapter {// ResourceServerConfigurerAdapter {

    @Autowired
    private MingUrlsConfiguration   mingUrlsConfiguration;

    @Autowired
    private AjaxSecurityConfigurer  ajaxSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.formLogin()
                // 可以通过授权登录进行访问
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/signin")
                .and()
                .authorizeRequests();

        for (String url : mingUrlsConfiguration.getCollects()) {
            registry.antMatchers(url)
                    .permitAll();
        }

        registry.anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
        http.apply(ajaxSecurityConfigurer);
    }

}