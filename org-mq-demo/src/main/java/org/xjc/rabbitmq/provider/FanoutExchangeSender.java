package org.xjc.rabbitmq.provider;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by xjc on 2018-11-28.
 */
@Component
public class FanoutExchangeSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    int dots = 0;

    int count = 0;

    @Scheduled(cron = "0/6 * * * * *")
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }
        builder.append(Integer.toString(++count));
        String message = builder.toString();
        template.convertAndSend(fanout.getName(), "", message);
        System.out.println("[FanoutExchangeSender] Sent '" + message + "'");
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void test1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1:" + System.currentTimeMillis());
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void test2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2:" + System.currentTimeMillis());
    }


}
