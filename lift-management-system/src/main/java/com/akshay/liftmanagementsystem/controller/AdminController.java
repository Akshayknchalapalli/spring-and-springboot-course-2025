package com.akshay.liftmanagementsystem.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.liftmanagementsystem.springsecurity.CustomUserDetails;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	public ResponseEntity<Map<String, Object>> getAdminDashboard(@AuthenticationPrincipal CustomUserDetails userDetails) {
	    
	    if (userDetails == null) {
	        System.out.println("userDetails is NULL - Authentication failed");
	        return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
	    }

	    List<String> roles = userDetails.getAuthorities().stream()
	                                    .map(GrantedAuthority::getAuthority) 
	                                    .collect(Collectors.toList());

	    return ResponseEntity.ok(Map.of(
	        "message", "Welcome, " + userDetails.getUsername() + "! This is your dashboard",
	        "roles", roles
	    ));
	}
}
