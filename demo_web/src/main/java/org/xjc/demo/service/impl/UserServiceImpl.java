package org.xjc.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xjc.demo.bean.User;
import org.xjc.demo.jpa.UserRepository;
import org.xjc.demo.service.UserService;

import java.util.List;

/**
 * 用户管理实现类
 * Created by xjc on 2018-12-11.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有user
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * 通过id查询user
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    /**
     * 新增user
     *
     * @param user
     */
    @Override
    public void add(User user) {
        user.setId(null);
        userRepository.save(user);
    }

    /**
     * 删除指定id的user
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    /**
     * 修改指定id的user信息
     *
     * @param id
     * @param user
     */
    @Override
    public void modify(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }
}
