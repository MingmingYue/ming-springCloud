package com.service.user.permission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

public class PermissionPersister implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionPersister.class);

    @Autowired
    private PermissionAutoScanProxy permissionAutoScanProxy;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() instanceof AnnotationConfigApplicationContext) {
            LOG.info("Start to persist with following permission list...");
            LOG.info("------------------------------------------------------------");
            List<String> permissions = permissionAutoScanProxy.getPermissions();
            LOG.info("Permission={}", permissions);
            // 权限数据入库
            LOG.info("------------------------------------------------------------");
        }
    }
}