package com.konrad.brk.KFiszki.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public class UserDto {
    private String id;
    private String username;
    private String email;
    private List<String> flashcardsPackagesIds;

    public static final class Builder {
        private String id;
        private String username;
        private String email;
        private List<String> flashcardsPackagesIds;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder flashcardsPackagesIds(List<String> flashcardsPackagesIds) {
            this.flashcardsPackagesIds = flashcardsPackagesIds;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.id = this.id;
            userDto.email = this.email;
            userDto.flashcardsPackagesIds = this.flashcardsPackagesIds;
            userDto.username = this.username;
            return userDto;
        }

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


    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", flashcardsPackagesIds=" + flashcardsPackagesIds +
                '}';
    }
}
