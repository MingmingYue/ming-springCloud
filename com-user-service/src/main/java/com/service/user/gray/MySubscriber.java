package com.service.user.gray;

import com.google.common.eventbus.Subscribe;
import com.nepxion.discovery.common.entity.ParameterEntity;
import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import com.nepxion.discovery.plugin.framework.event.*;
import com.nepxion.eventbus.annotation.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@EventBus
public class MySubscriber {
    @Autowired
    private PluginAdapter pluginAdapter;

    @Subscribe
    public void onRuleUpdated(RuleUpdatedEvent ruleUpdatedEvent) {
        System.out.println("========== 规则执行更新, rule=" + ruleUpdatedEvent.getRule());
    }

    @Subscribe
    public void onRuleCleared(RuleClearedEvent ruleClearedEvent) {
        System.out.println("========== 规则执行清空");
    }

    @Subscribe
    public void onRuleRuleFailure(RuleFailureEvent ruleFailureEvent) {
        System.out.println("========== 规则更新失败, rule=" + ruleFailureEvent.getRule() + ", exception=" + ruleFailureEvent.getException());
    }

    @Subscribe
    public void onParameterChanged(ParameterChangedEvent parameterChangedEvent) {
        ParameterEntity parameterEntity = parameterChangedEvent.getParameterEntity();
        String serviceId = pluginAdapter.getServiceId();
        Map<String, String> parameter = null;
        if (parameterEntity != null) {
            Map<String, Map<String, String>> parameterMap = parameterEntity.getParameterMap();
            parameter = parameterMap.get(serviceId);
        }
        System.out.println("========== 获取动态参数, serviceId=" + serviceId + ", parameter=" + parameter);
    }

    @Subscribe
    public void onRegisterFailure(RegisterFailureEvent registerFailureEvent) {
        System.out.println("========== 注册失败, eventType=" + registerFailureEvent.getEventType() + ", eventDescription=" + registerFailureEvent.getEventDescription() + ", serviceId=" + registerFailureEvent.getServiceId() + ", host=" + registerFailureEvent.getHost() + ", port=" + registerFailureEvent.getPort());
    }
}