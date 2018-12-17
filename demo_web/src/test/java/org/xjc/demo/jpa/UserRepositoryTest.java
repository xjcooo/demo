package org.xjc.demo.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xjc.demo.bean.User;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<User> users;

    @Before
    public void setUp() throws Exception {
        testAdd();
    }

    @After
    public void tearDown() throws Exception {
        testDelete();
    }

//    @Test
    public void testAdd() {
        log.info("users add");
        User user = null;
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setAge(18 + i);
            user.setName("xjc" + i);
            userRepository.save(user);
        }
        users = userRepository.findAll();
        log();
    }

//    @Test
    public void testDelete() {
        log.info("users delete");
        for (User user : users) {
            userRepository.delete(user.getId());
        }
        log();
    }

    @Test
    public void testFindAllByName() {
        List<User> users = userRepository.findAllByName("xjc1");
        log(users);
    }

    private void log(List<User> users) {
        int count = 0;
        log.info("users-info");
        for (User user : users) {
            log.info("{}.{}", ++count, user);
        }
    }

    private void log() {
        int count = 0;
        log.info("users-info");
        for (User user : users) {
            log.info("{}.{}", ++count, user);
        }
    }
}