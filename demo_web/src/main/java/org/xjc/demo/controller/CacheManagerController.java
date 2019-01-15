package org.xjc.demo.controller;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xjc.demo.service.CacheManagerService;

import java.util.List;

/**
 * Created by xjc on 2019-1-2.
 */
@RestController
@RequestMapping("cacheManager")
@Slf4j
public class CacheManagerController {

    @Autowired
    private CacheManagerService cacheManagerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getCacheNames() {
        return cacheManagerService.getCacheNames();
    }

    @RequestMapping(value = "/{cacheName}/{key}", method = RequestMethod.GET)
    public Object getCacheValue(@PathVariable String cacheName, @PathVariable String key) {
        return cacheManagerService.get(Joiner.on("#").join(cacheName, key)).get();
    }
}
