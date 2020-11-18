package com.konrad.brk.KFiszki.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Document
public class Role{
    @Id
    private String id;
    private String name;
    private Collection<String> usersIds;

    public Role(String name) {
        this.name = name;
        this.usersIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(Collection<String> usersIds) {
        this.usersIds = usersIds;
    }
    
    public void addNewUserToRolesListById(String userId){
        usersIds.add(userId);
    }


    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", usersIds=" + usersIds +
                '}';
    }
}
