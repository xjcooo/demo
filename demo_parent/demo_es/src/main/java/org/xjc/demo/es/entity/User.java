package org.xjc.demo.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * 用户实体类
 *
 * Created by xjc on 2019-3-7.
 */
@Data
@Document(indexName = "users", type = "list")
public class User {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private Boolean male;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="GMT+8")
    private Date createTime;
    private Organization organization;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

}
