package com.gmail.oleynikn.hellocrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ngapp/").setViewName("forward:/ngapp/index.html");
        registry.addViewController("/ngapp/**/{[path:[^\\\\.]*}").setViewName("forward:/ngapp/index.html");
    }
}
