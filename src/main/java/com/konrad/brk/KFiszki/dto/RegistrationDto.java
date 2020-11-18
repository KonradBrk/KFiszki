package com.konrad.brk.KFiszki.dto;

import com.konrad.brk.KFiszki.model.Role;

import java.util.ArrayList;
import java.util.List;

//todo - data validation
public class RegistrationDto {
    private String username;
    private String password;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getFlashcardsPackagesIds() {
        return new ArrayList<>();
    }


    @Override
    public String toString() {
        return "RegistrationDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
