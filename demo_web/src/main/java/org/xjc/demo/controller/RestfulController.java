package org.xjc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjc.demo.configs.ConfigsBean;

@RestController
public class RestfulController {

    @Autowired
    private ConfigsBean configsBean;

    @RequestMapping("/")
    public String getName(){
        return configsBean.getName();
    }

    @RequestMapping("/demo/health")
    public String getHealth(){
        return "ok";
    }

    @RequestMapping("/demo/appName")
    public String getAppName(){return configsBean.getAppName();}
}
