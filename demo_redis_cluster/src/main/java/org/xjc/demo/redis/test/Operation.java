package org.xjc.demo.redis.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by xjc on 2019-5-14.
 */
public class Operation {

    private Jedis js;
    private JedisCluster jc;
    private boolean isCluster;

    public Operation() {
    }

    public Operation(String url) throws Exception {
        this(url, url.indexOf(",")>0);
    }

    public Operation(String url, boolean isCluster) throws Exception {
        init(url, isCluster);
    }

    /**
     * 初始化redis链接
     *
     * @param url redis服务器的地址,多个时用英文封号分隔,例如(ip1:port1,ip2:port2)
     * @param isCluster 是否是集群
     */
    public void init(String url, boolean isCluster) throws Exception {
        List<String> urls = getIpAndPort(url, ",");
        this.isCluster = isCluster;
        if (isCluster) {
            initCluster(urls);
        } else if (urls.size() == 1){
            initSingleNode(urls.get(0));
        } else {
            throw new Exception("单节点redis链接url格式错误");
        }
    }

    public void set(String key, String value){
        if (isCluster)
            jc.set(key, value);
        else
            js.set(key, value);
    }

    public String get(String key){
        return isCluster?jc.get(key):js.get(key);
    }

    public void delete(String key){
        if (isCluster)
            jc.del(key);
        else
            js.del(key);
    }

    public void hset(String key, String field, String value){
        if (isCluster)
            jc.hset(key, field, value);
        else
            js.hset(key, field, value);
    }

    public String hget(String key, String field){
        return isCluster?jc.hget(key, field):js.hget(key, field);
    }

    public void hmset(String key, Map map){
        if (isCluster)
            jc.hmset(key, map);
        else
            js.hmset(key, map);
    }

    public List<String> hmget(String key, String... fields){
        return isCluster?jc.hmget(key, fields):js.hmget(key, fields);
    }

    public void initSingleNode(String url) throws Exception {
        List<String> ipAndPort = getIpAndPort(url, ":");
        if (ipAndPort.size() != 2) throw new Exception("url格式错误");
        js = new Jedis(ipAndPort.get(0), Integer.valueOf(ipAndPort.get(1)));
    }

    public void initCluster(List<String> urls) throws Exception {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        for (String item : urls) {
            List<String> ipAndPort = getIpAndPort(item, ":");
            if (ipAndPort.size() != 2) throw new Exception("url格式错误");
            jedisClusterNodes.add(new HostAndPort(ipAndPort.get(0), Integer.valueOf(ipAndPort.get(1))));
            jc = new JedisCluster(jedisClusterNodes);
        }
    }

    private List<String> getIpAndPort(String url, String s) {
        List<String> list = new ArrayList<String>();
        String[] ss = url.split(s);
        for (int i = 0; i < ss.length; i++)
            list.add(ss[i].trim());
        return list;
        //        return Splitter.on(s).trimResults().omitEmptyStrings().splitToList(url);
    }
}
