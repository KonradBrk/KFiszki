package com.konrad.brk.KFiszki.service;

import com.konrad.brk.KFiszki.dto.RegistrationDto;
import com.konrad.brk.KFiszki.dto.UserAdminUpdateDto;
import com.konrad.brk.KFiszki.exception.UserAlreadyExistsException;
import com.konrad.brk.KFiszki.exception.UserNotFoundException;
import com.konrad.brk.KFiszki.model.FlashcardPackage;
import com.konrad.brk.KFiszki.model.Role;
import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User addUser(RegistrationDto registrationDto) {
        if (getOptUserByUsername(registrationDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists!");
        }
        String password = passwordEncoder.encode(registrationDto.getPassword());
        User user = User.apply(registrationDto, password, "ROLE_USER");
        return userRepository.save(user);
    }

    private Optional<User> getOptUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getUserByUsername(String name) {
        return getOptUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("There is no user with name: " + name));
    }

    public User getUserById(String id) {
        return userRepository.findById(id).stream()
                .findFirst().orElseThrow(() -> new UserNotFoundException("There is no user with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(String id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public void addFlashcardPackageToTheUser(String username, FlashcardPackage flashcardPackage){
        User user = getUserByUsername(username);
        user.getFlashcardsPackagesIds().add(flashcardPackage.getId());
        userRepository.save(user);
    }

    public User updateUser(User user, UserAdminUpdateDto userAdminUpdateDto) {
        if (userAdminUpdateDto.getUsername() != null) user.setUsername(userAdminUpdateDto.getUsername());
        if (userAdminUpdateDto.getPassword() != null) user.setPassword(passwordEncoder.encode(userAdminUpdateDto.getPassword()));
        if (userAdminUpdateDto.getEmail() != null) user.setEmail(userAdminUpdateDto.getEmail());
        if (userAdminUpdateDto.getRole() != null){
            user.setRole(userAdminUpdateDto.getRole());
            updateAuthority(userAdminUpdateDto.getRole());
        }
        return userRepository.save(user);
    }

    private void updateAuthority(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority(role));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @Override
    public UserDetails loadUserByUsername(String s){
        return (UserDetails) getUserByUsername(s);
    }


}
