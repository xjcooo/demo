package org.xjc.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xjc.demo.rabbitmq.customer.FanoutExchangeReceiver;
import org.xjc.demo.rabbitmq.customer.TopicExchangeReceiver;
import org.xjc.demo.rabbitmq.customer.WorkQueuesReceiver;

/**
 * Created by xjc on 2018-11-6.
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "spring.boot.amqp.xjc.test.hello";
    public static final String QUEUE_NAME_WORK_QUEUES = "spring.boot.amqp.xjc.test.workQueues";

    public static final String EXCHANGE_NAME_TOPIC = "spring.boot.amqp.xjc.test.topicExchange";
    public static final String QUEUE_NAME_TOPIC_1 = "spring.boot.amqp.xjc.test.topic.Queue1";
    public static final String QUEUE_NAME_TOPIC_2 = "spring.boot.amqp.xjc.test.topic.Queue2";
    public static final String ROUTING_NAME_TOPIC1 = "spring.boot.amqp.xjc.test.topic1.#";
    public static final String ROUTING_NAME_TOPIC11 = "spring.boot.amqp.xjc.test.topic1.queue";
    public static final String ROUTING_NAME_TOPIC12 = "spring.boot.amqp.xjc.test.topic1.queue.v2";
    public static final String ROUTING_NAME_TOPIC2 = "spring.boot.amqp.xjc.test.topic2.*";
    public static final String ROUTING_NAME_TOPIC3 = "spring.boot.amqp.xjc.test.topic2.#";
    public static final String ROUTING_NAME_TOPIC01 = "spring.boot.amqp.xjc.test.topic2.queue";
    public static final String ROUTING_NAME_TOPIC02 = "spring.boot.amqp.xjc.test.topic2.queue.v2";

    public static final String EXCHANGE_NAME_FANOUT = "spring.boot.amqp.xjc.test.fanoutExchange";


    @Bean
    public Queue getTestQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    // work queues
    @Bean
    public Queue hello() {
        return new Queue(QUEUE_NAME_WORK_QUEUES);
    }

    private static class workQueueReceiverConfig {

        @Bean
        public WorkQueuesReceiver receiver1() {
            return new WorkQueuesReceiver(1);
        }

        @Bean
        public WorkQueuesReceiver receiver2() {
            return new WorkQueuesReceiver(2);
        }
    }
    // work queues
    // topic exchange

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(EXCHANGE_NAME_TOPIC);
    }

    private static class topicExchangeConfig {

        @Bean
        public TopicExchangeReceiver receiver() {
            return new TopicExchangeReceiver();
        }

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new Queue(QUEUE_NAME_TOPIC_2);
        }

        @Bean
        public Binding binding1a(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with(ROUTING_NAME_TOPIC1);
        }

        @Bean
        public Binding binding1b(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with(ROUTING_NAME_TOPIC2);
        }

        @Bean
        public Binding binding2a(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with(ROUTING_NAME_TOPIC3);
        }
    }

    // topic exchange
    // fanout exchange  --  publish/subscribe
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(EXCHANGE_NAME_FANOUT);
    }

    private static class fanoutExchangeConfig {

        @Bean
        public Queue fanoutExchangeQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue fanoutExchangeQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue fanoutExchangeQueue1) {
            return BindingBuilder.bind(fanoutExchangeQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout,
                                Queue fanoutExchangeQueue2) {
            return BindingBuilder.bind(fanoutExchangeQueue2).to(fanout);
        }

        @Bean
        public FanoutExchangeReceiver receiver() {
            return new FanoutExchangeReceiver();
        }
    }
    // fanout exchange  --  publish/subscribe

}
