package org.xjc.demo.common.lock;

/**
 * 分布式锁生成器
 *
 * 单例情况下
 *
 * Created by xjc on 2019-2-26.
 */
public interface LockKeyGenerator<T> {

    /**
     * 锁key生成算法
     *
     * @return
     */
    String getKey(T t);

}
