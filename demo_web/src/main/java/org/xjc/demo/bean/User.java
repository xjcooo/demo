package org.xjc.demo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xjc on 2018-12-4.
 */
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
