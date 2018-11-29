package org.xjc.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.application")
public class ConfigsBean {

    private String appName;

    private String name;

    public String getAppName() {
        return appName;
    }

    public String getName() {
        return name;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setName(String name) {
        this.name = name;
    }
}




