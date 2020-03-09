package org.xjc.demo.lock;

import com.google.common.base.Preconditions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import org.xjc.demo.common.annotation.DistributedLock;
import org.xjc.demo.common.annotation.DistributedParam;
import org.xjc.demo.common.lock.LockKeyGenerator;
import org.xjc.demo.common.util.StringsUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 切面对象的拦截锁生成器
 *
 * Created by xjc on 2019-2-26.
 */
public class LockAopKeyGenerator implements LockKeyGenerator<ProceedingJoinPoint> {

    @Override
    public String getKey(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock lockAnnotation = method.getAnnotation(DistributedLock.class);

        Preconditions.checkArgument(StringsUtil.isNotEmpty(lockAnnotation.prefix()), "lock's prefix should not be null.");
        final Object[] args = proceedingJoinPoint.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //默认解析方法里面带 DistributedParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final DistributedParam annotation = parameters[i].getAnnotation(DistributedParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(lockAnnotation.delimiter()).append(args[i]);
        }
        if (StringsUtil.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final DistributedParam annotation = field.getAnnotation(DistributedParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return builder.insert(0, lockAnnotation.prefix()).toString();
    }
}
