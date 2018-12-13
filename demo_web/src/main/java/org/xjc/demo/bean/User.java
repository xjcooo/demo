package org.xjc.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xjc on 2018-12-4.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4896600822149149221L;
    private Long id;
    private String name;
    private Integer age;

}
