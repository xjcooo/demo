package org.xjc.demo.es.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xjc on 2019-3-13.
 */
public class AlarmGrafanaMessageTemplate implements Serializable {

    private static final long serialVersionUID = 1846061283756738315L;

    private Long ruleId;
    private String ruleName;
    private String ruleUrl;
    private String state;
    private AlarmType type;
    private String title;
    private String imageUrl;
    private String message;
    private List<EvalMatch> evalMatches;

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public void setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        setType(AlarmType.getType(state));
    }

    public AlarmType getType() {
        if (type == null)
            type = AlarmType.getType(state);
        return type;
    }

    public void setType(AlarmType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EvalMatch> getEvalMatches() {
        return evalMatches;
    }

    public void setEvalMatches(List<EvalMatch> evalMatches) {
        this.evalMatches = evalMatches;
    }

    @Override
    public String toString() {
        return "AlarmGrafanaMessageTemplate{" +
                "ruleId=" + ruleId +
                ", ruleName='" + ruleName + '\'' +
                ", ruleUrl='" + ruleUrl + '\'' +
                ", state='" + state + '\'' +
                ", type=" + getType() +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", message='" + message + '\'' +
                ", evalMatches=" + evalMatches +
                '}';
    }
}
