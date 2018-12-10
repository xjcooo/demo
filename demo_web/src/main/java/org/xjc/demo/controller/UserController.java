package org.xjc.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.xjc.demo.bean.User;

import java.util.*;

/**
 * Created by xjc on 2018-12-4.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    //线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<User> getUserList() {
        return new ArrayList<User>(users.values());
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
