package org.xjc.demo.rabbitmq.config;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by xjc on 2018-11-14.
 */
public class JavaMqFactory {

    public static final String EXCHANGE_NAME = "exchange";
    public static final String QUEUE_NAME = "queue";
    public static final String ROUTING_KEY = "routing2";


    public static void main(String[] args)  {
        try {
//            for (int i = 0; i < 100; i++)
//                createMessage("message" + i);
            useMessage(5);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void createMessage(String message) throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection(new Address[]{new Address("localhost", 5672)});
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct", false);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        channel.close();
        connection.close();
    }

    private static void useMessage(int waitSeconds) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection(new Address[]{new Address("localhost", 5672)});
        final Channel channel = connection.createChannel();
        channel.basicQos(64);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer message:" + new String(body));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, consumer);

        try {
            TimeUnit.SECONDS.sleep(waitSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.close();
        connection.close();
    }

}
