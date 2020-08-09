package com.core.config.register;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;

/**
 * @author xiaoMing
 * Create on 2020-08-06.
 */
public class MyConsulServiceRegistry extends ConsulServiceRegistry {

    public MyConsulServiceRegistry(ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler,
                                   HeartbeatProperties heartbeatProperties) {
        super(client, properties, ttlScheduler, heartbeatProperties);
    }

    @Override
    public void register(ConsulRegistration reg) {
//        if (reg.getService().getAddress().contains("192.168")) {
//            reg.getService().setAddress("10.10.35.236");
//            reg.getService().getCheck().setHttp("http://10.10.35.236:" + reg.getPort() + "/actuator/health");
//        }
        reg.getService().setId(reg.getService().getName() + "-" + reg.getService().getAddress() + "-" + reg.getService().getPort());
        super.register(reg);
    }
}