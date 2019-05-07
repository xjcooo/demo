package org.xjc.demo.controller;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置测试
 *
 * @RefreshScope 类下的配置内容支持动态刷新
 *
 * Created by xjc on 2019-5-7.
 */
@RequestMapping("config")
@RestController
@RefreshScope
public class ConfigController {

    @Value("${app.title}")
    private String title;

    @RequestMapping(value = "{config}", method = RequestMethod.GET)
    public String getConfig(@PathVariable String config){
        return Joiner.on(" ").join(title, config);
    }

}
