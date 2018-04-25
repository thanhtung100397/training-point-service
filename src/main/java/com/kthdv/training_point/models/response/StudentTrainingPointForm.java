package com.kthdv.training_point.models.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kthdv.training_point.models.entity.TrainingPointForm;
import com.kthdv.training_point.models.entity.User;

import java.lang.reflect.Type;
import java.util.Map;

public class StudentTrainingPointForm {
    private String studentID;
    private String studentName;
    private String studentUsername;
    private long lastModified;
    private Map<String, Integer> data;

    public StudentTrainingPointForm(User student, TrainingPointForm trainingPointForm) {
        setStudentID(student.getId());
        setStudentName(student.getFullName());
        setStudentUsername(student.getUsername());
        setLastModified(trainingPointForm.getLastModified().getTime());
        setData(trainingPointForm.getData());
    }

    public StudentTrainingPointForm(){

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

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    public void setData(String data) {
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();
        this.data = gson.fromJson(data, type);
    }
}
