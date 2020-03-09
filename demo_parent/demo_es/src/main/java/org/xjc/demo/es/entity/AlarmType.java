package org.xjc.demo.es.entity;

/**
 * Created by xjc on 2019-3-13.
 */
public enum AlarmType {

    OK("ok"),
    PAUSED("paused"),
    ALERTING("alerting"),
    PENDING("pending"),
    NO_DATA("no_data"),
    ;

    // ok, paused, alerting, pending, no_data
    private String name;
    AlarmType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AlarmType getType(String name){
        for (AlarmType type : AlarmType.values()) {
            if (type.getName().equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }
}
