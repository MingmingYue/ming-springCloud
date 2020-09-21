package com.service.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@FeignClient(value = "discovery-guide-service-a")
public interface AFeign {
    @GetMapping(path = "/invoke/{value}")
    String invoke(@PathVariable(value = "value") String value);

    @GetMapping(path = "/invoke-async/{value}")
    String invokeAsync(@PathVariable(value = "value") String value);
}