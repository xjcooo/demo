package org.xjc.demo.es.entity;

import lombok.Getter;

/**
 * Created by xjc on 2019-4-3.
 */
@Getter
public enum OrgLevel {

    LOWER("低级", "lower"),MIDDLE("中级", "middle"),HIGHER("高级", "higher");

    private String name;
    private String type;

    OrgLevel(String name, String type){
        this.name = name;
        this.type = type;
    }

}
