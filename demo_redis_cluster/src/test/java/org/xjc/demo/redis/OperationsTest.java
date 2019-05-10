package org.xjc.demo.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
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

}
