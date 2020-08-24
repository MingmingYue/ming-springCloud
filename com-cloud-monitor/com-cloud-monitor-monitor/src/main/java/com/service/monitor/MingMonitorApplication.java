package com.service.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author xiaoMing
 * Create on 2020-08-22.
 */
@SpringCloudApplication
public class MingMonitorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MingMonitorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MingMonitorApplication.class, args);
    }

}
