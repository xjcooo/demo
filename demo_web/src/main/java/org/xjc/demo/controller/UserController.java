package org.xjc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xjc.demo.bean.User;
import org.xjc.demo.service.UserService;

import java.util.List;

/**
 * Created by xjc on 2018-12-4.
 */
@RestController
@RequestMapping("/users")
public class UserController {

//    //线程安全的Map
//    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<User> getUserList() {
        return userService.getUsers();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        userService.add(user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.modify(id, user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "success";
    }
}
