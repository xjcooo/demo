package org.xjc.rabbitmq.customer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xjc.rabbitmq.bean.Person;
import org.xjc.rabbitmq.config.RabbitMqConfig;

/**
 * Created by xjc on 2018-11-6.
 */
@Service
public class Receiver {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(Person person){
        System.out.println("message:<"+person+">");
    }

}
