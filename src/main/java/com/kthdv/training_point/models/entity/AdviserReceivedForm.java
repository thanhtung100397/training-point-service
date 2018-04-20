package com.kthdv.training_point.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "adviser_received_form")
public class AdviserReceivedForm {
    @Id
    private String userID;
    private Date createdDate;

    public AdviserReceivedForm(String userID) {
        this.userID = userID;
        setCreatedDate(new Date());
    }

    public AdviserReceivedForm() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
