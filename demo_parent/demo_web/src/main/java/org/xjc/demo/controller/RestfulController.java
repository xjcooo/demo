package org.xjc.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xjc.demo.configs.ConfigsBean;

/**
 * restful测试
 */
@Api(value = "Rest控制类", consumes = "xjcooo", produces = "xjc", description = "restful测试控制类")
@RestController
@RequestMapping("/demo")
public class RestfulController {

    @Autowired
    private ConfigsBean configsBean;

    /**
     * 获取项目名
     */
    @ApiOperation(value = "获取项目名称", notes = "测试exception统一处理")
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getName() throws Exception {
        if (1 == 1) {
            throw new Exception("获取项目名称失败");
        }
        return configsBean.getName();
    }

    /**
     * 健康监测
     */
    @ApiOperation(value = "健康监测", notes = "测试应用健康状态")
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String getHealth() {
        return "ok";
    }

    /**
     * 获取应用名
     */
    @ApiOperation(value = "获取应用名", notes = "查询当前应用的信息")
    @RequestMapping(value = "/appName", method = RequestMethod.GET)
    public String getAppName() {
        return configsBean.getAppName();
    }
}
