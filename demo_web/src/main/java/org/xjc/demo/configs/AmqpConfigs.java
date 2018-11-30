package org.xjc.demo.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq队列绑定配置
 */
@Configuration
public class AmqpConfigs {

    /**
     * 新建队列
     * @return
     */
    @Bean
    public Queue getQueue(){
        return new Queue("spring.boot.amqp.xjc.test.workQueues", true, false, false);
    }

}
