package com.service.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.Collection;

/**
 * Created by Steven on 2019/10/26.
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final AuthServerConfiguration authServerConfiguration;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(s -> new UserDetails() {
            private static final long serialVersionUID = -7427270897995086798L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return AuthorityUtils.createAuthorityList("USER");
            }

            @Override
            public String getPassword() {
                return passwordEncoder().encode(authServerConfiguration.getPassword());
            }

            @Override
            public String getUsername() {
                return authServerConfiguration.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
