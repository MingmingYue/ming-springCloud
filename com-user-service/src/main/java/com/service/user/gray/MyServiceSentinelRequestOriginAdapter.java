package com.service.user.gray;


import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.nepxion.discovery.plugin.strategy.service.sentinel.adapter.DefaultServiceSentinelRequestOriginAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义版本号+用户名，实现组合式熔断
 *
 * @author xiaoMing
 * Create on 2020-08-07.
 */
public class MyServiceSentinelRequestOriginAdapter extends DefaultServiceSentinelRequestOriginAdapter {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String version = request.getHeader(DiscoveryConstant.N_D_SERVICE_VERSION);
        String user = request.getHeader("user");

        return version + "&" + user;
    }
}