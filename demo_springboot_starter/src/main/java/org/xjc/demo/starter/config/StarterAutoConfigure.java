package org.xjc.demo.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xjc.demo.starter.service.StarterService;

/**
 * 自动装配类
 *
 *  @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 *  @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 *  @ConditionalOnProperty(prefix = "demo.starter.service", value = "enabled", havingValue = "true")，当配置文件中example.service.enabled=true时。
 *
 * Created by xjc on 2018-12-20.
 */
@Configuration
@ConditionalOnClass(StarterService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "demo.starter.service", value = "enabled", havingValue = "true")
    StarterService starterService (){
        return new StarterService(properties.getConfig());
    }

}
