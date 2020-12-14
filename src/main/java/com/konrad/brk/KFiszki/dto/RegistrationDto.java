package com.konrad.brk.KFiszki.dto;

import com.mongodb.lang.NonNull;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDto {
    @NonNull
    @Size(min=3,max=25,message = "Username must have from 3 to 25 characters.")
    private String username;
    @NonNull
    @Size(min=3,max=25,message = "Password must have from 3 to 25 characters.")
    private String password;
    @NonNull
    @Email(message = "Wrong email has been provided.")
    private String email;

    public RegistrationDto(@NonNull String username, @NonNull String password, @NonNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
