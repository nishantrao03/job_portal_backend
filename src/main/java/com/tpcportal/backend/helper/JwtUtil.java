package com.tpcportal.backend.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("your_new_base64_encoded_secret_key_here".getBytes());
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Retrieve rollno from jwt token
    public String extractRollno(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Retrieve expiration date from jwt token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        System.out.println("Debug 8.4");
        boolean temp = extractExpiration(token).before(new Date());
        System.out.println(temp);
        return extractExpiration(token).before(new Date());
    }

    // Generate token for rollno
    public String generateToken(String rollno) {
        System.out.println("Debug 2.1");
        Map<String, Object> claims = new HashMap<>();
        System.out.println("Debug 2.2");
        return createToken(claims, rollno);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        System.out.println("Debug 2.3");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractRollno(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
