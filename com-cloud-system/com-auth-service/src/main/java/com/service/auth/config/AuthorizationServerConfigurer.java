package com.service.auth.config;

import com.core.commons.constants.SecurityConstant;
import com.service.auth.component.JwtTokenEnhancer;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven on 2019/10/26.
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManagerBean;
    private final AuthServerConfiguration authServerConfiguration;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(authServerConfiguration.getClientId())
                .secret(passwordEncoder.encode(authServerConfiguration.getClientSecret()))
                .resourceIds("user-service")
                .authorizedGrantTypes(SecurityConstant.REFRESH_TOKEN, SecurityConstant.PASSWORD)
                .scopes(authServerConfiguration.getScope())
                .authorities("ROLE_ADMIN")
                //访问令牌有效期 单位: 秒
                .accessTokenValiditySeconds(7200)
                //刷新令牌有效期 单位: 秒
                .refreshTokenValiditySeconds(7200*3)
                //自动授权配置
                .autoApprove(true)
                //重定向地址
                .redirectUris("https://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);
        endpoints
                .authenticationManager(authenticationManagerBean)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("demojwt.jks"), "keystorepass".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "keypairpass".toCharArray());
    }

    /**
     * 跨域, 开发环境使用 vue-cli 代理，正式用nginx
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        @SuppressWarnings("unchecked")
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
