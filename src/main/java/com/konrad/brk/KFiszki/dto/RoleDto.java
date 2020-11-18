package com.konrad.brk.KFiszki.dto;

import java.util.Collection;

public class RoleDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
