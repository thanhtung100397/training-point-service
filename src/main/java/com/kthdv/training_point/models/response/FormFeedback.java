package com.kthdv.training_point.models.response;

import com.kthdv.training_point.models.entity.AdviserFeedback;
import com.kthdv.training_point.models.entity.MonitorFeedback;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FormFeedback {
    @ApiModelProperty(notes = "sender date", position = 2)
    private long createdDate;
    @ApiModelProperty(notes = "feedback state, maybe: \"accepted\", \"rejected\"", position = 3)
    private String state;

    public FormFeedback(MonitorFeedback monitorFeedback) {
        setCreatedDate(monitorFeedback.getCreatedDate().getTime());
        setState(monitorFeedback.getState());
    }

    public FormFeedback(AdviserFeedback adviserFeedback) {
        setCreatedDate(adviserFeedback.getCreatedDate().getTime());
        setState(adviserFeedback.getState());
    }

    public FormFeedback() {
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
