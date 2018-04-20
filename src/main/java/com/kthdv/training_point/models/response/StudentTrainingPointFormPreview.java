package com.kthdv.training_point.models.response;

import com.kthdv.training_point.models.entity.User;

import java.util.Date;

public class StudentTrainingPointFormPreview {
    private String studentID;
    private String studentName;
    private long lastModified;

    public StudentTrainingPointFormPreview(User student, Date lastMofified) {
        setStudentID(student.getId());
        setStudentName(student.getFullName());
        setLastModified(lastMofified.getTime());
    }

    public StudentTrainingPointFormPreview(){

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

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
