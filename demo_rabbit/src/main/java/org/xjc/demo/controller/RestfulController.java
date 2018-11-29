package org.xjc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjc.demo.configs.Constants;

@RestController
public class RestfulController {

    @Autowired
    private Constants constants;

    @RequestMapping("/")
    public String getAppName(){
        System.out.println(constants.appName);
        return Constants.appName;
    }

    @RequestMapping("/demo/health")
    public String getHealth(){
        return "ok";
    }

    @RequestMapping("/demo/appName")
    public String getName(){return constants.getName();}
}
