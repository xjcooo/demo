package org.xjc.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xjc.demo.bean.User;

/**
 * Created by xjc on 2018-12-17.
 */

public interface UserRepository extends JpaRepository<User, Long> {


}
