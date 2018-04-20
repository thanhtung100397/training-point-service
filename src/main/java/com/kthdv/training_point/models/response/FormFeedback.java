package com.kthdv.training_point.models.response;

import com.kthdv.training_point.models.entity.AdviserFeedback;
import com.kthdv.training_point.models.entity.MonitorFeedback;
import com.kthdv.training_point.models.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class FormFeedback {
    @ApiModelProperty(notes = "full name of sender")
    private String senderName;
    @ApiModelProperty(notes = "role of sender",position = 1)
    private String senderRole;
    @ApiModelProperty(notes = "sender date", position = 2)
    private long createdDate;
    @ApiModelProperty(notes = "feedback state, maybe: \"accepted\", \"rejected\"", position = 3)
    private String state;

    public FormFeedback(User sender, MonitorFeedback monitorFeedback) {
        setSenderName(sender.getFullName());
        setSenderRole(sender.getRole());
        setCreatedDate(monitorFeedback.getCreatedDate().getTime());
        setState(monitorFeedback.getState());
    }

    public FormFeedback(User sender, AdviserFeedback adviserFeedback) {
        setSenderName(sender.getFullName());
        setSenderRole(sender.getRole());
        setCreatedDate(adviserFeedback.getCreatedDate().getTime());
        setState(adviserFeedback.getState());
    }

    public FormFeedback() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
