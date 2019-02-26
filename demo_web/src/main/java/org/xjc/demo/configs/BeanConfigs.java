package org.xjc.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xjc.demo.common.lock.LockKeyGenerator;
import org.xjc.demo.lock.LockAopKeyGenerator;

/**
 * Created by xjc on 2019-2-26.
 */
@Configuration
public class BeanConfigs {

    @Bean
    public LockKeyGenerator lockKeyGenerator(){
        return new LockAopKeyGenerator();
    }

}
