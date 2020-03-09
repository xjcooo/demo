package org.xjc.demo.es.entity;

/**
 * Created by xjc on 2019-3-13.
 */
public class EvalMatch {

    private String value;
    private String metric;
    private Tags tags;

    class Tags {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "EvalMatch{" +
                "value='" + value + '\'' +
                ", metric='" + metric + '\'' +
                ", tags=" + tags +
                '}';
    }
}
