package com.service.user.web.controller;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.service.user.core.CoreImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "discovery-guide-service-a")
public class ARestImpl extends CoreImpl {
    private static final Logger LOG = LoggerFactory.getLogger(ARestImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/rest/{value}")
    public String rest(@PathVariable(value = "value") String value) {
        value = getPluginInfo(value);
        value = restTemplate.getForEntity("http://discovery-guide-service-b/rest/" + value, String.class).getBody();

        LOG.info("调用路径：{}", value);

        return value;
    }
}