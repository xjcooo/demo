package org.xjc.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.application")
public class Constants {

    public static String appName;

    private String name;

    public String getName() {
        return name;
    }

}




