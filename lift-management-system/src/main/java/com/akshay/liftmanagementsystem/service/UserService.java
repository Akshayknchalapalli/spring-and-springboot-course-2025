package com.akshay.liftmanagementsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.liftmanagementsystem.entity.User;
import com.akshay.liftmanagementsystem.entity.Authority;
import com.akshay.liftmanagementsystem.repository.AuthorityRepository;
import com.akshay.liftmanagementsystem.repository.UserRepository;

import java.util.Collections;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    // Constants for role names
    private static final String ROLE_USER = "ROLE_USER";

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(String username, String rawPassword) {
        // Validate inputs
        Objects.requireNonNull(username, "Username cannot be null");
        Objects.requireNonNull(rawPassword, "Password cannot be null");

        if (username.isEmpty() || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        // Check if the username already exists
        if (userRepository.existsById(username)) {
            throw new RuntimeException("Username already exists!");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Create and save the user
        User user = new User(username, encodedPassword, true, Collections.emptySet());
        userRepository.save(user);

        // Assign ROLE_USER by default
        Authority authority = new Authority(user, ROLE_USER);
        authorityRepository.save(authority);

        return user;
    }
}