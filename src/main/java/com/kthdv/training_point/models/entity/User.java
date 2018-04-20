package com.kthdv.training_point.models.entity;

import com.kthdv.training_point.models.dto.UserDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    public static final String STUDENT_ROLE = "student";
    public static final String MONITOR_ROLE = "monitor";
    public static final String ADVISER_ROLE = "adviser";

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String fullName;
    private String username;
    private String password;
    private String role;

    public User(UserDto userDto) {
        setFullName(userDto.getFullName());
        setUsername(userDto.getUsername());
        setPassword(userDto.getPassword());
        setRole(userDto.getRole());
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
