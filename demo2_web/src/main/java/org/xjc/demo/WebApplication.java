package org.xjc.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xjc on 2019-7-19.
 */
@Slf4j
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        log.info("web starting ...");
        SpringApplication.run(WebApplication.class, args);
        log.info("web started.");
    }

}
