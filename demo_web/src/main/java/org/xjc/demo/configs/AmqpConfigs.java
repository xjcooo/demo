package org.xjc.demo.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfigs {

    @Bean
    public Queue getQueue(){
        return new Queue("spring.boot.amqp.xjc.test.workQueues", true, false, false);
    }

}
