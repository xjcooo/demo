package org.xjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xjc on 2018-12-7.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @ResponseBody
    @GetMapping
    public String hello() throws Exception {
        throw new Exception("hello error!");
    }

    @GetMapping("/")
    public String hello2() throws Exception {
        throw new Exception("hello2 error");
    }

}
