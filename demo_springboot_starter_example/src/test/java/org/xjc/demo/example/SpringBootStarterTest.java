package org.xjc.demo.example;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xjc.demo.example.service.SpringStartDemo;
import org.xjc.demo.starter.service.StarterService;

/**
 * Created by xjc on 2018-12-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootStarterTest {

    @Autowired
    private SpringStartDemo SpringStartDemo;
    @Autowired
    private StarterService starterService;

    @Test
    public void testStarter() {
        SpringStartDemo.split();
    }

    @Test
    public void testStarted() {
        String[] rs = starterService.split(",");
        log.info("size={}, strings={}",rs.length, Joiner.on("|").join(rs));
        rs = starterService.split("-");
        log.info("size={}, strings={}",rs.length, Joiner.on("|").join(rs));
    }

}
