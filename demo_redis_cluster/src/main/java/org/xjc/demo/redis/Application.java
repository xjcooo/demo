package org.xjc.demo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;
import org.xjc.demo.redis.test.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2019-5-10.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
//        try {
//            test(args[0], Boolean.valueOf(args[1]));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    static String singleNode = "10.0.30.114:6379";
    static String clusterNode = "10.0.30.114:7000,10.0.30.62:7000,10.0.30.183:7000";

    public static void test(String field_prefix, boolean isCluster) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String key = "key", field = "feild7", value = "WarnConditionStrategy{WarnStrategy{warnId=133762, userId=9, stockName=平安银行, stockCode=000001, securityType=4, exchange=SZ, warnValue=0.00, strategyType=9, strategyState=1, isDelete=0, triggerCount=0, availableTimeBegin=Thu Jan 01 09:30:00 CST 1970, availableTimeEnd=Thu Jan 01 15:00:00 CST 1970, businessType=2, testFlag=0}, customerNo=130082, trackType=4, trackCode=000001, trackName=平安银行, trackExchange=SZ, weekBegin=1, weekEnd=5, deviationCtrlParam=DeviationCtrlEnabledParam{option=NON_ORDER, limit=2.00}, dataVersion=2, strategyPropertiesObj=GridStrategyProperties{upperLimitPrice=15.00, lowerLimitPrice=10.00, buyQuantity=100, sellQuantity=100, increaseSeqType=0, decreaseSeqType=0, increase=0.50, decrease=-0.50, reactionRatio=-0.04, rallyRatio=0.01, buyStrategy=6, sellStrategy=7, aboveUpperPriceSettings=0, belowLimitPriceSettings=0, multipliedEntrust=1, brokenPriceTriggerOption=1}, dynamicPropertiesObj=GridDynamicProperties{basePrice=12.81, isBuySignal=false, isSellSignal=false}, confirmCount=0, confirmDelayType=2, confirmDelayTimes=5, entrustMethod=0, orderType=0, exchangeMethod=2, nodeValue=TJD;iPhone 6;;13800000009;, openness=1}";
        Operation redis = new Operation(isCluster ? clusterNode : singleNode);
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 10000; i++) {
            map.put(field_prefix + field + i, value + i);
        }
        redis.hmset(key, map);
        stopWatch.stop();
        System.out.println("spendTime : " + stopWatch.getTotalTimeMillis());
    }

}
