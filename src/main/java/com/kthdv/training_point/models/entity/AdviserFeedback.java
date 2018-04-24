package com.kthdv.training_point.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "adviser_feedback")
public class AdviserFeedback {
    @Id
    private String userID;
    private String state;
    private Date createdDate;

    public AdviserFeedback(String userID, String state) {
        this.userID = userID;
        this.state = state;
        setCreatedDate(new Date());
    }

    public AdviserFeedback() {
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
