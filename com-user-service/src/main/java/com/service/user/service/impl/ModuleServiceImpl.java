package com.service.user.service.impl;

import com.service.common.cache.AdminCacheKey;
import com.service.common.jpa.JPAFactoryImpl;
import com.service.user.entity.Module;
import com.service.user.entity.QModule;
import com.service.user.service.ModuleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Service(value = "moduleService")
@CacheConfig(cacheNames = AdminCacheKey.MODULE_INFO)
public class ModuleServiceImpl extends JPAFactoryImpl implements ModuleService {

    @Override
    @Cacheable(key = "'module_list'")
    public List<Module> getAllList() {
        QModule qModule = QModule.module;
        return this.queryFactory.selectFrom(qModule).fetch();
    }

}
