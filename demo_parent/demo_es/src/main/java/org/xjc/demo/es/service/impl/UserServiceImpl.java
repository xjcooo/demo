package org.xjc.demo.es.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xjc.demo.es.entity.User;
import org.xjc.demo.es.repository.UserRepository;
import org.xjc.demo.es.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Created by xjc on 2019-3-7.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public List<User> queryAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public List<User> query(User user) {
        return Lists.newArrayList();
    }

    public User getOne(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.isPresent()?userOptional.get():null;
    }
}
