package com.konrad.brk.KFiszki.controler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.konrad.brk.KFiszki.dto.RegistrationDto;
import com.konrad.brk.KFiszki.dto.UserAdminUpdateDto;
import com.konrad.brk.KFiszki.dto.UserDto;
import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.service.RoleService;
import com.konrad.brk.KFiszki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.EXPIRATION_TIME;
import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.SECRET;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers().stream().map(user ->
                new UserDto.Builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .flashcardsPackagesIds(user.getFlashcardsPackagesIds())
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/users/{name}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        User user = userService.getUserByUsername(name);
        UserDto userDto = new UserDto.Builder()
                .id(user.getId())
                .email(user.getEmail())
                .flashcardsPackagesIds(user.getFlashcardsPackagesIds())
                .username(user.getUsername())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Username with id: " + id + " has been removed.");
    }

    @PatchMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUserById(@RequestBody UserAdminUpdateDto userAdminUpdateDto, @PathVariable String id){
        User user = userService.getUserById(id);
        User updatedUser = userService.updateUser(user, userAdminUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody RegistrationDto registrationDto){
        User user = userService.addUser(registrationDto);
        roleService.addUserToRoleCollection(user);
        String token = JWT.create()
                .withSubject(registrationDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);

    }

}
