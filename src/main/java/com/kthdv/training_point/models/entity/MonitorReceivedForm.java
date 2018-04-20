package com.kthdv.training_point.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "monitor_received_form")
public class MonitorReceivedForm {
    @Id
    private String userID;
    private Date createdDate;

    public MonitorReceivedForm(String userID) {
        this.userID = userID;
        setCreatedDate(new Date());
    }

    public MonitorReceivedForm() {
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
