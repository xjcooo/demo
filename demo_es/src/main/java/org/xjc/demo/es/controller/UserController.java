package org.xjc.demo.es.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjc.demo.es.entity.User;
import org.xjc.demo.es.service.UserService;

import java.util.List;

/**
 * 用户管理
 *
 * Created by xjc on 2019-5-6.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public String add(@RequestBody String userStr){
        User user = new Gson().fromJson(userStr, User.class);
        userService.save(user);
        return "true";
    }

    @RequestMapping("update")
    public String update(@RequestBody String userStr){
        User user = new Gson().fromJson(userStr, User.class);
        userService.update(user);
        return "true";
    }

    @RequestMapping("delete")
    public String delete(Long id){
        userService.delete(new User(id));
        return "true";
    }

    @RequestMapping("queryAll")
    public List<User> queryAll(){
        return userService.queryAll();
    }
}
