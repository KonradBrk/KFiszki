package com.konrad.brk.KFiszki.service;

import com.konrad.brk.KFiszki.exception.UserRoleAlreadyExistsException;
import com.konrad.brk.KFiszki.model.Role;
import com.konrad.brk.KFiszki.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getOptRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }

    public Role addRole(String roleName, String userId){
        if (getOptRoleByName(roleName).isPresent()) {
            throw new UserRoleAlreadyExistsException("Role already exists!");
        }
        Role role = new Role(roleName);
        if(!userId.isEmpty()){
            role.addNewUserToRolesListById(userId);
        }
        return roleRepository.save(role);
    }
}
