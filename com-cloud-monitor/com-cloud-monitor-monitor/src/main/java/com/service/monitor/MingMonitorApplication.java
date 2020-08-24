package com.service.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author xiaoMing
 * Create on 2020-08-22.
 */
@SpringBootApplication
public class MingMonitorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MingMonitorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MingMonitorApplication.class, args);
    }

}
