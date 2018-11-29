package org.xjc.rabbitmq.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xjc.rabbitmq.bean.Person;
import org.xjc.rabbitmq.config.RabbitMqConfig;

/**
 * Created by xjc on 2018-11-6.
 */

@Service
public class Queuer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String name){
        amqpTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, new Person(name));
    }
}
