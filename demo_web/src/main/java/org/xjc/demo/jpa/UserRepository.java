package org.xjc.demo.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xjc.demo.bean.User;

import java.util.List;

/**
 * Created by xjc on 2018-12-17.
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);

    @Cacheable(value = "userList", key = "methodName")
    List<User> findAll();
}
