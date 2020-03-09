package org.xjc.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * Created by xjc on 2019-5-10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> stringRedis;

    @PostConstruct
    public void init(){
        stringRedis = stringRedisTemplate.opsForValue();
    }

    @Test
    public void test(){
//        stringRedis.set("name", "丁洁");
        System.out.println(stringRedis.get("name1"));
    }
}
