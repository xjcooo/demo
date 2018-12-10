package org.xjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjc.demo.exception.PageException;

import java.util.Random;

/**
 * Created by xjc on 2018-12-7.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @ResponseBody
    @GetMapping
    public String hello() throws Exception {
        if (new Random().nextInt(2) == 1) throw new Exception("hello error!");
        return "hello";
    }

    @GetMapping("/")
    @ResponseBody
    public String hello2() throws Exception {
        if (new Random().nextInt(2) == 1) throw new Exception("hello2 error");
        return "hello2";
    }

    @GetMapping("/index")
    public String indexPage(ModelMap model) throws Exception {
        model.addAttribute("url", "localhost:8080");
        return "index";
    }


    @GetMapping("/home")
    public String homePage(ModelMap model) throws PageException {
        model.addAttribute("url", "localhost:8080");
        if (new Random().nextInt(2) == 1) throw new PageException("home error");
        return "home";
    }
}
