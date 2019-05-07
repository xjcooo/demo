package org.xjc.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xjc on 2019-1-31.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Consumer {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test() {
        for (String str : new String[]{"dd", "gg", "xjc", "yq"}) {
            String result = restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?name=" + str, String.class);
            log.info("Return : {}", result);
        }
    }



}
