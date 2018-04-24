package com.kthdv.training_point.models.response;

import com.kthdv.training_point.models.entity.AdviserFeedback;
import com.kthdv.training_point.models.entity.MonitorFeedback;
import com.kthdv.training_point.models.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FormFeedback {
    @ApiModelProperty(notes = "id of student")
    private String studentID;
    @ApiModelProperty(notes = "name of student", position = 2)
    private String studentName;
    @ApiModelProperty(notes = "username of student", position = 3)
    private String studentUsername;
    @ApiModelProperty(notes = "sender date", position = 4)
    private long createdDate;
    @ApiModelProperty(notes = "feedback state, maybe: \"accepted\", \"rejected\"", position = 5)
    private String state;

    public FormFeedback(User user, MonitorFeedback monitorFeedback) {
        setStudentID(user.getId());
        setStudentName(user.getFullName());
        setStudentUsername(user.getUsername());
        setCreatedDate(monitorFeedback.getCreatedDate().getTime());
        setState(monitorFeedback.getState());
    }

    public FormFeedback(User user, AdviserFeedback adviserFeedback) {
        setStudentID(user.getId());
        setStudentName(user.getFullName());
        setStudentUsername(user.getUsername());
        setCreatedDate(adviserFeedback.getCreatedDate().getTime());
        setState(adviserFeedback.getState());
    }

    public FormFeedback() {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
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
