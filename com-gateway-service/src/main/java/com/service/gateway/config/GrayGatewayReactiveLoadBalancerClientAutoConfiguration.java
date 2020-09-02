package com.service.gateway.config;

import com.service.gateway.filter.GrayReactiveLoadBalancerClientFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoMing
 * Create on 2020-09-01.
 */
@Configuration
public class GrayGatewayReactiveLoadBalancerClientAutoConfiguration {

    public GrayGatewayReactiveLoadBalancerClientAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({GrayReactiveLoadBalancerClientFilter.class})
    public GrayReactiveLoadBalancerClientFilter grayReactiveLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        return new GrayReactiveLoadBalancerClientFilter(clientFactory, properties);
    }
}
