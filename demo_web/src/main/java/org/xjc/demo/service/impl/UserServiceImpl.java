package org.xjc.demo.service.impl;

import org.springframework.stereotype.Component;
import org.xjc.demo.bean.User;
import org.xjc.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理实现类
 * Created by xjc on 2018-12-11.
 */
@Component
public class UserServiceImpl implements UserService {

    /**
     * 获取所有user
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * 通过id查询user
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return users.get(id);
    }

    /**
     * 新增user
     *
     * @param user
     */
    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }

    /**
     * 删除指定id的user
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        users.remove(id);
    }

    /**
     * 修改指定id的user信息
     *
     * @param id
     * @param user
     */
    @Override
    public void modify(Long id, User user) {
        User origin = users.get(id);
        origin.setName(user.getName());
        origin.setAge(user.getAge());
        users.put(id, origin);
    }
}
