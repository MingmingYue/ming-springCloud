package com.service.user.core;

import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaoMing
 * Create on 2020-09-15.
 */
public class CoreImpl {
    @Autowired
    private PluginAdapter pluginAdapter;

    public String getPluginInfo(String value) {
        return pluginAdapter.getPluginInfo(value);
    }
}
