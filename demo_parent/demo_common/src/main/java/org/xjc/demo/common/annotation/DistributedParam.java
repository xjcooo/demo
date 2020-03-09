package org.xjc.demo.common.annotation;

import java.lang.annotation.*;

/**
 * Created by xjc on 2019-2-26.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributedParam {

    /**
     * 字段名
     *
     * @return
     */
    String name() default "";

}
