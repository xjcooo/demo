package org.xjc.demo.rabbitmq.bean;

import java.io.Serializable;

/**
 * Created by xjc on 2018-11-7.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 7575982976145045319L;
    private Integer id;
    private String name;
    private boolean sex;

    public Person() {
        id = 0;
        name = "xjc";
        sex = true;
    }

    public Person(String name) {
        id = 0;
        this.name = name;
        sex = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
