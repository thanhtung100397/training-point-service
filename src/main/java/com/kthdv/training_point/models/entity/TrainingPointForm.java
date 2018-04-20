package com.kthdv.training_point.models.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "training_point_form")
public class TrainingPointForm {
    @Id
    private String userID;
    private String data;
    private Date lastModified;

    public TrainingPointForm(String userID, Map<String, Integer> formData) {
        setUserID(userID);
        setData(formData);
        setLastModified(new Date());
    }

    public TrainingPointForm() {
    }

    public void update(Map<String, Integer> data) {
        setData(data);
        setLastModified(new Date());
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData(Map<String, Integer> data) {
        Gson gson = new GsonBuilder().create();
        this.data = gson.toJson(data);
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
