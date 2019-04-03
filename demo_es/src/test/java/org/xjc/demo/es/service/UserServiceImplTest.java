package org.xjc.demo.es.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xjc.demo.es.entity.OrgLevel;
import org.xjc.demo.es.entity.Organization;
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
        user.setOrganization(new Organization(Long.valueOf(10), "商务部", OrgLevel.HIGHER));
        userService.save(user);
        user = userService.getOne(Long.valueOf(4));
        Assert.assertNotNull(user);
        System.out.println(user.toString());
        Assert.assertEquals(user.getName(), "xjcooo");
    }

    @Test
    public void delete() {
        userService.delete(new User(Long.valueOf(1)));
        List<User> list = userService.queryAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setName("xjpooo");
        user.setAge(100);
        user.setMale(true);
        user.setCreateTime(new Date());
        user.setOrganization(new Organization(Long.valueOf(20), "销售部", OrgLevel.MIDDLE));
        userService.update(user);
        Assert.assertEquals(userService.getOne(Long.valueOf(1)).getName(), "xjpooo");
    }

    @Test
    public void queryAll() {
        List<User> list = userService.queryAll();
        for (User user : list) {
            System.out.println(user);
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void query() {

    }

    @Test
    public void getOne() {
        Assert.assertEquals(userService.getOne(Long.valueOf(1)).getName(), "xjpooo");
    }
}
