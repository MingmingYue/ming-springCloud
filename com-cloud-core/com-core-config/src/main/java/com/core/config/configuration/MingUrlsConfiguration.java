package com.core.config.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有需要过滤URL的配置，urls.collects 模式
 *
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Configuration
@ConditionalOnExpression("!'${urls}'.isEmpty()")
@ConfigurationProperties(prefix = "urls")
public class MingUrlsConfiguration {

    private List<String> collects = new ArrayList<>();

    public List<String> getCollects() {
        return collects;
    }

    public void setCollects(List<String> collects) {
        this.collects = collects;
    }

}