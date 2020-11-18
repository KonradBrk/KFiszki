package com.konrad.brk.KFiszki.controler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.konrad.brk.KFiszki.dto.RegistrationDto;
import com.konrad.brk.KFiszki.dto.RoleDto;
import com.konrad.brk.KFiszki.model.Role;
import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.EXPIRATION_TIME;
import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.SECRET;

@RestController
@RequestMapping("/api")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/newrole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> addNewRole(@RequestBody RoleDto roleDto){
        Role role = roleService.createNewRole(roleDto); //todo
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(role);

    }
}
