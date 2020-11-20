package com.konrad.brk.KFiszki.model;

import com.konrad.brk.KFiszki.dto.RegistrationDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    private String role;
    private List<String> flashcardsPackagesIds;



    public static User apply(RegistrationDto registrationDto, String password, String role){
        User user = new User();
        user.username = registrationDto.getUsername();
        user.password = password;
        user.email = registrationDto.getEmail();
        user.role = role;
        user.flashcardsPackagesIds = registrationDto.getFlashcardsPackagesIds();
        return user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFlashcardsPackagesIds() {
        return flashcardsPackagesIds;
    }

    public void setFlashcardsPackagesIds(List<String> flashcardsPackagesIds) {
        this.flashcardsPackagesIds = flashcardsPackagesIds;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rolesId=" + role +
                ", flashcardsPackagesIds=" + flashcardsPackagesIds +
                '}';
    }
}
