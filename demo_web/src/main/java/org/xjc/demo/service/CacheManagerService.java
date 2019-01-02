package org.xjc.demo.service;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * cacheManagerService
 *
 * 缓存的管理服务类
 *
 * Created by xjc on 2019-1-2.
 */
@Service
public interface CacheManagerService extends Cache {

    List<String> getCacheNames();

}
