package org.xjc.demo.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

/**
 * Created by xjc on 2018-11-8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorTest {

    public static final String KEY = "/xjc/test/zsorted";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();

    @Test
    public void deleteKey() {
        Set<String> rs = stringRedisTemplate.keys("userList:*");
        for (String key : rs) {
            System.out.println(key);
        }
//        redisTemplate.delete(rs);
    }

    @Test
    public void test() {
        redisTemplate.opsForZSet().add(KEY, "mybatis", 10);
        redisTemplate.opsForZSet().add(KEY, "hibernate", 9);
        redisTemplate.opsForZSet().add(KEY, "oracle", 8);
        redisTemplate.opsForZSet().add(KEY, "oracle", 7);
        for (int i = 0; i < 10; i++) {
            redisTemplate.opsForZSet().add(KEY, "type" + i, i);
        }
        // for values
        println();

        // remove value by value
        redisTemplate.opsForZSet().remove(KEY, "oracle");
        println();

        // delete key
        redisTemplate.delete(KEY);
        println();
    }

    private void println() {
        Set<ZSetOperations.TypedTuple<String>> result = redisTemplate.opsForZSet().rangeWithScores(KEY, 0, 10 - 1);
        System.out.println("result=");
        for (ZSetOperations.TypedTuple<String> v : result) {
            System.out.println(v.getScore() + " " + v.getValue());
        }
        System.out.println("+++++++++++++++++++++++");
        Set<String> rs = redisTemplate.opsForZSet().range(KEY, 0, 10 - 1);
        System.out.println("result=");
        for (String v : rs) {
            System.out.println(v);
        }
        System.out.println("=======================");
    }

    @Test
    public void test2(){
        Object object = redisTemplate.opsForHash().get("AMP_ALARM_ACCOUNT", "sms_MODULE_UNIQUE");
        System.out.println(object);
        Cursor<Map.Entry<Object, Object>> mapCursor = redisTemplate.opsForHash().scan("AMP_ALARM_ACCOUNT", null);
        Map.Entry<Object, Object> entry;
        while (mapCursor.hasNext()){
            entry = mapCursor.next();
            System.out.println("[entry] key : " + entry.getKey() + ", value : " + entry.getValue());
        }
    }

}
