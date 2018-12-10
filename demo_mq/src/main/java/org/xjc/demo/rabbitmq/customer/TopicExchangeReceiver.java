package org.xjc.demo.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;
import org.xjc.demo.rabbitmq.config.RabbitMqConfig;

/**
 * Created by xjc on 2018-11-28.
 */
public class TopicExchangeReceiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME_TOPIC_2)
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws
            InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        doWork(in);
        watch.stop();
        System.out
                .println("TopicExchangeReceiver " + receiver + " [x] Received '" + in + "' Done in "
                        + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(900);
            }
        }
    }
}
