package org.xjc.demo.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xjc.demo.redis.test.Operation;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xjc on 2019-5-10.
 */

public class OperationsTest {

    private JedisCluster jc;

    @Before
    public void init(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("10.0.30.62", 7000));
        jedisClusterNodes.add(new HostAndPort("10.0.30.114", 7000));
        jedisClusterNodes.add(new HostAndPort("10.0.30.183", 7000));
        jedisClusterNodes.add(new HostAndPort("10.0.30.62", 7001));
        jedisClusterNodes.add(new HostAndPort("10.0.30.114", 7001));
        jedisClusterNodes.add(new HostAndPort("10.0.30.183", 7001));
        jc = new JedisCluster(jedisClusterNodes);
    }

    @Test
    public void test(){
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);
    }

    @Test
    public void testOperate() throws Exception {

        Operation redis = new Operation("10.0.30.114:7000, 10.0.30.62:7001");
        String key = "key", field = "feild", value = "value";
        redis.set(key, value);
        Assert.assertEquals(redis.get(key), value);
        redis.delete(key);
        Assert.assertNull(redis.get(key));


        List<String> fields = new ArrayList<String>();
        for (int i = 0; i < 100; i++){
            redis.hset(key, field + i, value + i );
            fields.add(field+i);
        }
        System.out.println(redis.hget(key, field+0));
        String[] ss = new String[100];fields.toArray(ss);
        System.out.println(redis.hmget(key, ss));
        redis.delete(key);
    }

}
