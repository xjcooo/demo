package org.xjc.demo.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;
import org.xjc.demo.redis.test.Operation;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by xjc on 2019-5-10.
 */

public class OperationsTest {

    private JedisCluster jc;

    @Before
    public void init() {
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
    public void test() {
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);
    }

    @Test
    public void testOperate() throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Operation redis = new Operation("10.0.30.114:6379");
        String key = "key", field = "feild", value = "WarnConditionStrategy{WarnStrategy{warnId=133762, userId=9, stockName=平安银行, stockCode=000001, securityType=4, exchange=SZ, warnValue=0.00, strategyType=9, strategyState=1, isDelete=0, triggerCount=0, availableTimeBegin=Thu Jan 01 09:30:00 CST 1970, availableTimeEnd=Thu Jan 01 15:00:00 CST 1970, businessType=2, testFlag=0}, customerNo=130082, trackType=4, trackCode=000001, trackName=平安银行, trackExchange=SZ, weekBegin=1, weekEnd=5, deviationCtrlParam=DeviationCtrlEnabledParam{option=NON_ORDER, limit=2.00}, dataVersion=2, strategyPropertiesObj=GridStrategyProperties{upperLimitPrice=15.00, lowerLimitPrice=10.00, buyQuantity=100, sellQuantity=100, increaseSeqType=0, decreaseSeqType=0, increase=0.50, decrease=-0.50, reactionRatio=-0.04, rallyRatio=0.01, buyStrategy=6, sellStrategy=7, aboveUpperPriceSettings=0, belowLimitPriceSettings=0, multipliedEntrust=1, brokenPriceTriggerOption=1}, dynamicPropertiesObj=GridDynamicProperties{basePrice=12.81, isBuySignal=false, isSellSignal=false}, confirmCount=0, confirmDelayType=2, confirmDelayTimes=5, entrustMethod=0, orderType=0, exchangeMethod=2, nodeValue=TJD;iPhone 6;;13800000009;, openness=1}";
        redis.set(key, value);
        Assert.assertEquals(redis.get(key), value);
        redis.delete(key);
        Assert.assertNull(redis.get(key));


        List<String> fields = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            redis.hset(key, field + i, value + i);
            fields.add(field + i);
        }
        System.out.println(redis.hget(key, field + 0));
        String[] ss = new String[10000];
        fields.toArray(ss);
        System.out.println(redis.hmget(key, ss));
        redis.delete(key);

        stopWatch.stop();
        System.out.println("spendTime : " + stopWatch.getTotalTimeMillis());
    }

    /*******************************************************/

    String singleNode = "10.0.30.114:6379";
    String clusterNode = "10.0.30.114:7000,10.0.30.62:7000,10.0.30.183:7000";

    boolean isCluster = true;

    @Test
    public void testOperate2() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String key = "key", field = "feild7", value = "WarnConditionStrategy{WarnStrategy{warnId=133762, userId=9, stockName=平安银行, stockCode=000001, securityType=4, exchange=SZ, warnValue=0.00, strategyType=9, strategyState=1, isDelete=0, triggerCount=0, availableTimeBegin=Thu Jan 01 09:30:00 CST 1970, availableTimeEnd=Thu Jan 01 15:00:00 CST 1970, businessType=2, testFlag=0}, customerNo=130082, trackType=4, trackCode=000001, trackName=平安银行, trackExchange=SZ, weekBegin=1, weekEnd=5, deviationCtrlParam=DeviationCtrlEnabledParam{option=NON_ORDER, limit=2.00}, dataVersion=2, strategyPropertiesObj=GridStrategyProperties{upperLimitPrice=15.00, lowerLimitPrice=10.00, buyQuantity=100, sellQuantity=100, increaseSeqType=0, decreaseSeqType=0, increase=0.50, decrease=-0.50, reactionRatio=-0.04, rallyRatio=0.01, buyStrategy=6, sellStrategy=7, aboveUpperPriceSettings=0, belowLimitPriceSettings=0, multipliedEntrust=1, brokenPriceTriggerOption=1}, dynamicPropertiesObj=GridDynamicProperties{basePrice=12.81, isBuySignal=false, isSellSignal=false}, confirmCount=0, confirmDelayType=2, confirmDelayTimes=5, entrustMethod=0, orderType=0, exchangeMethod=2, nodeValue=TJD;iPhone 6;;13800000009;, openness=1}";
        Map<String, String> map = new HashMap<String, String>();
        Operation redis = new Operation(isCluster ? clusterNode : singleNode);
        for (int i = 0; i < 10000; i++) {
            map.put(field + i, value + i);
        }
        redis.hmset(key, map);
        stopWatch.stop();
        System.out.println("spendTime : " + stopWatch.getTotalTimeMillis());
    }

}
