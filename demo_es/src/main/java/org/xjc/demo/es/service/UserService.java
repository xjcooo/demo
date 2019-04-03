package org.xjc.demo.es.service;

import org.springframework.stereotype.Service;
import org.xjc.demo.es.entity.User;

import java.util.List;

/**
 * Created by xjc on 2019-3-7.
 */
@Service
public interface UserService {

    void save(User user);

    void delete(User id);

    void update(User user);

    List<User> queryAll();

    List<User> query(User user);

    User getOne(Long id);
}
