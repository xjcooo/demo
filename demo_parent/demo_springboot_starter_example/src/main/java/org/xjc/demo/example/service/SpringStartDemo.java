package org.xjc.demo.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xjc.demo.starter.service.StarterService;

/**
 * Created by xjc on 2018-12-20.
 */
@Component
@Slf4j
public class SpringStartDemo {

    @Autowired
    private StarterService starterService;

    public void split() {
        String[] rs = starterService.split(",");
        log.info("{}", rs);
        rs = starterService.split("-");
        log.info("{}", rs);

    }

}
