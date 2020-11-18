package com.konrad.brk.KFiszki.controler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.konrad.brk.KFiszki.dto.RegistrationDto;
import com.konrad.brk.KFiszki.dto.UserDto;
import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.EXPIRATION_TIME;
import static com.konrad.brk.KFiszki.config.JWT.SecurityConstants.SECRET;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
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
    public ResponseEntity<String> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Username with id: " + id + " has been removed.");
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody RegistrationDto registrationDto){
        userService.addUser(registrationDto);
        String token = JWT.create()
                .withSubject(registrationDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);

    }

}
