package org.xjc.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
 * <p>
 * 上述使用说明:
 * hibernate延时加载
 * 因为jsonplugin用的是Java的内审机制.hibernate会给被管理的pojo加入一个hibernateLazyInitializer属性,
 * jsonplugin会把hibernateLazyInitializer也拿出来操作,并读取里面一个不能被反射操作的属性就产生了这个异常. 
 * <p>
 * Created by xjc on 2018-12-4.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "hw_user")
public class User implements Serializable {

    private static final long serialVersionUID = 4896600822149149221L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

}
