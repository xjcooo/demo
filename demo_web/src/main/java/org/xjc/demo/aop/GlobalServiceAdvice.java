package org.xjc.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xjc on 2018-12-11.
 */
@Aspect
@Component
public class GlobalServiceAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalServiceAdvice.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * org.xjc.demo.service..*.*(..))")
    public void serviceLog() {
    }

    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info("[GlobalServiceAdvice] before, method={}", joinPoint.getSignature().getName());
    }

    @After("serviceLog()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        logger.info("[GlobalServiceAdvice] after, method={}, spend {}", joinPoint.getSignature().getName(), System.currentTimeMillis() - startTime.get());
    }

    // 不能使用JoinPoint入参
    @AfterReturning(pointcut = "serviceLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("[GlobalServiceAdvice] afterReturning, return={},spend {}", ret, System.currentTimeMillis() - startTime.get());
    }

    // 不能使用JoinPoint入参
    @AfterThrowing(pointcut = "serviceLog()", throwing = "ex")
    public void doAfterThrowing(Throwable ex) throws Throwable {
        logger.error("[GlobalServiceAdvice] afterThrowing, method={}, throwable={}", ex.getMessage());
    }

    @Around("serviceLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[GlobalServiceAdvice] aroundBefore, {}", joinPoint.getSignature().getName());
        Object rs = joinPoint.proceed();
        logger.info("[GlobalServiceAdvice] aroundAfter, {}", rs);
        return rs;
    }
}
