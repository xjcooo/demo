package org.xjc.demo.es.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xjc.demo.es.entity.AlarmGrafanaMessageTemplate;

/**
 * Created by xjc on 2019-3-13.
 */
@Slf4j
public class EnumTest {

    @Test
    public void test(){
        String msg = "{\"evalMatches\":[{\"value\":1501,\"metric\":\"Count\",\"tags\":{}},{\"value\":1501,\"metric\":\"Max costTime\",\"tags\":{}},{\"value\":1501,\"metric\":\"Min costTime\",\"tags\":{}}],\"message\":\"max(costTime) is too long.\",\"ruleId\":1,\"ruleName\":\"响应数据统计 alert\",\"ruleUrl\":\"http://localhost:8880/d/BmnwPFCiz/dashboardtest?fullscreen\\u0026edit\\u0026tab=alert\\u0026panelId=4\\u0026orgId=1\",\"state\":\"alerting\",\"title\":\"[Alerting] 响应数据统计 alert\"}";
        Gson gson = new GsonBuilder().create();
        AlarmGrafanaMessageTemplate template = gson.fromJson(msg, AlarmGrafanaMessageTemplate.class);
        log.info("{}", template);
    }

}
