package org.xjc.demo.rabbitmq.test;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.xjc.demo.rabbitmq.config.RabbitMqConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by xjc on 2018-11-21.
 */
public class QueueNoSpringTest {

    private static Channel channel;
    private static Connection connection;

    private static void createChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        connection = factory.newConnection(new Address[]{new Address("localhost", 5672)});
        channel = connection.createChannel();
    }

    private static void queueDeclare(String queueName) throws IOException {
        channel.queueDeclare(queueName, true, false, false, null);
    }

    private static void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    @Test
    public void testSendWorkQueues() throws IOException, TimeoutException {
        createChannel();
//        queueDeclare(RabbitMqConfig.QUEUE_NAME_WORK_QUEUES);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", RabbitMqConfig.QUEUE_NAME_WORK_QUEUES, MessageProperties.PERSISTENT_TEXT_PLAIN, ("xjc."+i).getBytes());
        }
        close();
    }

    @Test
    public void testSendTopicExchange() throws IOException, TimeoutException {
        createChannel();
//        queueDeclare(RabbitMqConfig.QUEUE_NAME_WORK_QUEUES);
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_TOPIC, RabbitMqConfig.ROUTING_NAME_TOPIC11, MessageProperties.PERSISTENT_TEXT_PLAIN, RabbitMqConfig.ROUTING_NAME_TOPIC11.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_TOPIC, RabbitMqConfig.ROUTING_NAME_TOPIC12, MessageProperties.PERSISTENT_TEXT_PLAIN, RabbitMqConfig.ROUTING_NAME_TOPIC12.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_TOPIC, RabbitMqConfig.ROUTING_NAME_TOPIC01, MessageProperties.PERSISTENT_TEXT_PLAIN, RabbitMqConfig.ROUTING_NAME_TOPIC01.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_TOPIC, RabbitMqConfig.ROUTING_NAME_TOPIC02, MessageProperties.PERSISTENT_TEXT_PLAIN, RabbitMqConfig.ROUTING_NAME_TOPIC02.getBytes());
        close();
    }

}
