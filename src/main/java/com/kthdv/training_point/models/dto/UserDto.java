package com.kthdv.training_point.models.dto;

import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.validation.StringIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel
public class UserDto {
    @NotEmpty
    @ApiModelProperty(notes = "full name of user, NOT NULL, NOT EMPTY")
    private String fullName;
    @NotEmpty
    @ApiModelProperty(notes = "username for account, NOT NULL, NOT EMPTY", position = 1)
    private String username;
    @NotEmpty
    @ApiModelProperty(notes = "password for account, NOT NULL, NOT EMPTY", position = 2)
    private String password;
    @StringIn({User.STUDENT_ROLE, User.MONITOR_ROLE, User.ADVISER_ROLE})
    @ApiModelProperty(notes = "role for account, MUST be one of: \"student\", \"monitor\", \"adviser\"", position = 3)
    private String role;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
