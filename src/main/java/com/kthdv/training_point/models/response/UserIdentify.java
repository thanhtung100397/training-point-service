package com.kthdv.training_point.models.response;

public class UserIdentify {
    private String userID;
    private String role;

    public UserIdentify(String userID, String role) {
        this.userID = userID;
        this.role = role;
    }

    public UserIdentify() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
