package org.xjc.demo.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置项
 *
 * Created by xjc on 2018-12-20.
 */
@ConfigurationProperties("demo.starter.service")
public class StarterServiceProperties {

    private String config;

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
