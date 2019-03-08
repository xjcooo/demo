package org.xjc.demo.es.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xjc.demo.es.entity.User;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xjc on 2019-3-7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setId(Long.valueOf(4));
        user.setName("xjcooo");
        user.setAge(100);
        user.setMale(true);
        user.setCreateTime(new Date());
        userService.save(user);
        user = userService.getOne(Long.valueOf(4));
        System.out.println(user.toString());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "xjcooo");
    }

    @Test
    public void delete() {
        userService.delete(Long.valueOf(1));
        List<User> list = userService.queryAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setName("lssooo");
        user.setAge(100);
        user.setMale(true);
        userService.save(user);
        Assert.assertEquals(userService.getOne(Long.valueOf(1)).getName(), "lssooo");
    }

    @Test
    public void queryAll() {
        List<User> list = userService.queryAll();
        for (User user : list) {
            System.out.println(user);
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void query() {

    }

    @Test
    public void getOne() {
        Assert.assertEquals(userService.getOne(Long.valueOf(1)).getName(), "xjcooo");
    }
}