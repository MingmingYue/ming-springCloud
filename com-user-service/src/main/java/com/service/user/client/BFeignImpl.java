package com.service.user.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.service.user.core.CoreImpl;
import com.service.user.permission.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@RestController
// @Permission(name = "BFeign", label = "BFeign label", description = "BFeign description")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "discovery-guide-service-b")
public class BFeignImpl extends CoreImpl implements BFeign {
    private static final Logger LOG = LoggerFactory.getLogger(BFeignImpl.class);

    @Override
    @SentinelResource(value = "sentinel-resource", blockHandler = "handleBlock", fallback = "handleFallback")
    @Permission(name = "BFeign invoke", label = "BFeign invoke label", description = "BFeign invoke description")
    public String invoke(@PathVariable(value = "value") String value) {
        value = getPluginInfo(value);

        LOG.info("调用路径：{}", value);

        return value;
    }

    public String handleBlock(String value, BlockException e) {
        return value + "-> B server sentinel block, cause=" + e.getClass().getName() + ", rule=" + e.getRule() + ", limitApp=" + e.getRuleLimitApp();
    }

    public String handleFallback(String value) {
        return value + "-> B server sentinel fallback";
    }
}