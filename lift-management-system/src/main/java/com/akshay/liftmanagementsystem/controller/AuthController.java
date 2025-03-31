package com.akshay.liftmanagementsystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.akshay.liftmanagementsystem.entity.Authority;
import com.akshay.liftmanagementsystem.entity.User;
import com.akshay.liftmanagementsystem.repository.UserRepository;
import com.akshay.liftmanagementsystem.springsecurity.CustomUserDetails;
import com.akshay.liftmanagementsystem.springsecurity.CustomUserDetailsService;
import com.akshay.liftmanagementsystem.springsecurity.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          CustomUserDetailsService customUserDetailsService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String ,String> request) {
    	String username = request.get("username");
    	String password = request.get("password");
    	
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

        String token = jwtService.generateToken(Map.of(), customUserDetails);
        
        List<String> roles = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", customUserDetails.getUsername(),
                "roles", roles
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String , String> request) {
    	String username = request.get("username");
    	String password = request.get("password");
    	String role = request.get("role");
    	
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists!"));
        }
        if (!List.of("ROLE_ADMIN", "ROLE_USER").contains(role)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid role!"));
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); 
        newUser.setEnabled(true);
        
        Authority authority = new Authority();
        authority.setAuthority(role);
        authority.setUser(newUser);
        
        newUser.setAuthorities(Set.of(authority));
        
        
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
    }
    
    
}
