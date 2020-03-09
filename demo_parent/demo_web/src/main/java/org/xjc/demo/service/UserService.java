package org.xjc.demo.service;

import org.springframework.stereotype.Service;
import org.xjc.demo.bean.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理接口
 * Created by xjc on 2018-12-11.
 */
@Service
public interface UserService {

    Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    /**
     * 获取所有user
     *
     * @return
     */
    List<User> getUsers();

    /**
     * 通过id查询user
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 新增user
     *
     * @param user
     */
    void add(User user);

    /**
     * 删除指定id的user
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 修改指定id的user信息
     *
     * @param id
     * @param user
     */
    void modify(Long id, User user);

}
