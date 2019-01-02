package org.xjc.demo.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.xjc.demo.service.CacheManagerService;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by xjc on 2019-1-2.
 */
@Component
public class CacheManagerServiceImpl implements CacheManagerService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public List<String> getCacheNames() {
        return Lists.newArrayList(cacheManager.getCacheNames());
    }

    @Deprecated
    @Override
    public String getName() {
        return null;
    }

    @Deprecated
    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        List<String> keys = Splitter.on("#").splitToList(key.toString());
        return cacheManager.getCache(keys.get(0)).get(keys.get(1));
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
