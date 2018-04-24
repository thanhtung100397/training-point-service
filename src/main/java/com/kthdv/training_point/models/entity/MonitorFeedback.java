package com.kthdv.training_point.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "monitor_feedback")
public class MonitorFeedback {
    @Id
    private String userID;
    private String state;
    private Date createdDate;

    public MonitorFeedback(String userID, String state) {
        this.userID = userID;
        this.state = state;
        setCreatedDate(new Date());
    }

    public MonitorFeedback() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
