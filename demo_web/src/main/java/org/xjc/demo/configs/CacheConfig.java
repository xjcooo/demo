package org.xjc.demo.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * Created by xjc on 2018-12-17.
 */
//@EnableCaching
public class CacheConfig {

    public RedisCacheManager creatCache(){
        return null;
    }

}
