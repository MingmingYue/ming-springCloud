package com.core.register;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoMing
 * Create on 2020-08-06.
 */
@Configuration
public class RpsConsulServiceRegistryConfig {

    @Autowired(required = false)
    private TtlScheduler ttlScheduler;

    @Autowired(required = false)
    private HeartbeatProperties heartbeatProperties;

    @Bean
    public MyConsulServiceRegistry consulServiceRegistry(ConsulClient consulClient, ConsulDiscoveryProperties properties) {
        return new MyConsulServiceRegistry(
                consulClient, properties, ttlScheduler, heartbeatProperties);
    }
}
