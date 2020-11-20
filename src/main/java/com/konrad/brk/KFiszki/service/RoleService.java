package com.konrad.brk.KFiszki.service;


import com.konrad.brk.KFiszki.dto.RoleDto;
import com.konrad.brk.KFiszki.exception.UserRoleAlreadyExistsException;
import com.konrad.brk.KFiszki.exception.UserRoleNotFoundException;
import com.konrad.brk.KFiszki.model.Role;
import com.konrad.brk.KFiszki.model.User;
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

    public Role getRoleByRoleName(String name) {
        return getOptRoleByName(name)
                .orElseThrow(() -> new UserRoleNotFoundException("There is no role with name: " + name));
    }

    public Role addUserToRoleCollection(User user){
        Role role = getRoleByRoleName(user.getRole());
        role.addNewUserToRolesListById(user.getId());
        return roleRepository.save(role);
    }

    public Role createNewRole(RoleDto roleDto){
        Role role = Role.apply(roleDto);
        return roleRepository.save(role);
    }
}
