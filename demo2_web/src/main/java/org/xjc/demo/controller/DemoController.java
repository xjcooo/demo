package org.xjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xjc on 2019-7-19.
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() throws Exception {
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() throws Exception {
        return "index";
    }

}
