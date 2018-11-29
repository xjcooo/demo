package org.xjc.rabbitmq.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xjc.rabbitmq.config.RabbitMqConfig;

/**
 * Created by xjc on 2018-11-21.
 */
@Component
public class WorkQueuesSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    int dots = 0;
    int count = 0;

    @Scheduled(cron = "0/10 * * * * ?")
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
        rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME_WORK_QUEUES, message);
        System.out.println("[WorkQueueSender] Sent '" + message + "'," + this.hashCode()+"."+rabbitTemplate.hashCode());
    }

}
