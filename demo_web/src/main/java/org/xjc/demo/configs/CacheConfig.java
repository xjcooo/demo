package org.xjc.demo.configs;

import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2018-12-17.
 */
//@EnableCaching
@Configuration
public class CacheConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 修改redis缓存中key的生成方式
     *
     * @return
     */
    @Bean
    public CacheManager createCacheManager() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());


        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(60);// 过期时间
        cacheManager.setUsePrefix(true);
        cacheManager.setCachePrefix(new RedisCachePrefix() {
            private final RedisSerializer<String> serializer = new StringRedisSerializer();
            private final String delimiter = ":";

            public byte[] prefix(String cacheName) {
                return this.serializer
                        .serialize(cacheName.concat(this.delimiter));
            }
        });

        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("target", target.getClass().toGenericString());//放入target的名字
                map.put("method", method.getName());//放入method的名字
                if (params != null && params.length > 0) {//把所有参数放进去
                    int i = 0;
                    for (Object o : params) {
                        map.put("params-" + i, o);
                        i++;
                    }
                }
                String str = new GsonBuilder().create().toJson(map);
                byte[] hash = null;
                String s = null;
                try {
                    hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                s = MD5Encoder.encode(hash);//使用MD5生成位移key
                return "userRepository" + s;
            }
        };
    }

}
