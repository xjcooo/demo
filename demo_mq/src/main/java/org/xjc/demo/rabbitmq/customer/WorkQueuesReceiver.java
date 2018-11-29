package org.xjc.demo.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;
import org.xjc.demo.rabbitmq.config.RabbitMqConfig;

/**
 * Created by xjc on 2018-11-21.
 */
public class WorkQueuesReceiver {

    private final int tag;

    public WorkQueuesReceiver(int tag) {
        this.tag = tag;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME_WORK_QUEUES)
    public void receive(String msg){
        StopWatch watch = new StopWatch();
        watch.start();
        for (char item : msg.toCharArray()) {
            if ('.' == item) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        watch.stop();
        System.out.println("[WorkQueueReceiver] Tag["+tag+"] received '"+msg+"' Done in "+watch.getTotalTimeSeconds()+"s");
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME_WORK_QUEUES)
    public void receive2(String msg){
        StopWatch watch = new StopWatch();
        watch.start();
        for (char item : msg.toCharArray()) {
            if ('.' == item) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        watch.stop();
        System.out.println("[WorkQueueReceiver] Tag["+tag+0+"] received '"+msg+"' Done in "+watch.getTotalTimeSeconds()+"s");
    }

}
