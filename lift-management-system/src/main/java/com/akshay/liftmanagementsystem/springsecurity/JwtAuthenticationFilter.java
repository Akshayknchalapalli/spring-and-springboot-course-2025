package com.akshay.liftmanagementsystem.springsecurity;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final CustomUserDetailsService customUserDetailsService;

	public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
		this.jwtService = jwtService;
		this.customUserDetailsService = customUserDetailsService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String path = request.getRequestURI();
		 
		 if (path.startsWith("/api/auth/")) {
		        filterChain.doFilter(request, response);
		        return;
		    }
		 
		String authHeader = request.getHeader("Authorization");	
		
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
		    String token = authHeader.substring(7);
		    
		    String username = jwtService.extractUsername(token);

		    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

		        if (jwtService.validateToken(token, userDetails)) {
		            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		            SecurityContextHolder.getContext().setAuthentication(authentication);
		        } else {
		            System.out.println("Token Validation Failed");
		        }
		    }
		} catch (ExpiredJwtException e) {
			logger.warn("JWT Expired: {}", e.getMessage());
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
		    return;
		} catch (MalformedJwtException e) {
			logger.warn("Malformed JWT: {}", e.getMessage());
		    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Malformed Token");
		    return;
		} catch (JwtException e) {
			logger.warn("Invalid JWT: {}", e.getMessage());
		    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
		    return;
		}

		filterChain.doFilter(request, response);
		}
	}
