package org.xjc.demo.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.xjc.demo.common.annotation.DistributedLock;
import org.xjc.demo.common.lock.LockKeyGenerator;
import org.xjc.demo.exception.LockException;

import java.lang.reflect.Method;

/**
 * Created by xjc on 2019-2-26.
 */
@Aspect
@Slf4j
@Component
public class LockMethodInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private LockKeyGenerator lockAopKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(org.xjc.demo.common.annotation.DistributedLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        String lockKey = lockAopKeyGenerator.getKey(joinPoint);


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock lock = method.getAnnotation(DistributedLock.class);
        try {
            if (stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "")){
                log.info("获取锁[{}]成功", lockKey);
                stringRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            }
            else{
                log.info("获取锁[{}]失败", lockKey);
                throw new LockException("获取锁失败");
            }

            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        } finally {
            stringRedisTemplate.delete(lockKey);
        }
    }

}
