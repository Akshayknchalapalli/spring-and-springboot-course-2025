package com.akshay.liftmanagementsystem.springsecurity;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final String SECRET_KEY = "Akshaykunchalapalli@160699240898";
	
	public CustomUserDetails customUserDetails;
	
	public CustomUserDetailsService customUserDetailsService;
	
	public JwtService(CustomUserDetailsService customUserDetailsService) {
		super();
		this.customUserDetailsService = customUserDetailsService;
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	public String extractUsername(String token) {
		return extractClaim(token , Claims::getSubject);
	}

	

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
	public Authentication getAuthentication(String token) {
        String username = extractUsername(token);
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }
	
	 public String generateToken(Map<String, Object> extraClaims, CustomUserDetails CustomUserDetails) {
        return Jwts.builder()
                .claims()
                .add(extraClaims)
                .subject(CustomUserDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .and()
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();

    }
	 
	 public Boolean validateToken(String token ,UserDetails userDetails) {
		 final String username = extractUsername(token);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 }

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

}
