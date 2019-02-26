package org.xjc.demo.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解
 *
 * Created by xjc on 2019-2-26.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributedLock {

    /**
     * 锁key前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * 超时时间数值
     *
     * @return
     */
    int expire() default 5;

    /**
     * 超时时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 锁key的分隔符
     *
     * @return
     */
    String delimiter() default ":";

}
