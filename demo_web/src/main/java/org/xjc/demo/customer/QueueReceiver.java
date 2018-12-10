package org.xjc.demo.customer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = "spring.boot.amqp.xjc.test.workQueues")
    public void receiveMessage(String person) {
        System.out.println("message:<" + person + ">");
    }
}
