package org.xjc.demo.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.xjc.demo.es.entity.User;

/**
 * Created by xjc on 2019-3-7.
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
}
