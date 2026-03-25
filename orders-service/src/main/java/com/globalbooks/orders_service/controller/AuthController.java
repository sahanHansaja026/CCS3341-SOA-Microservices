package com.globalbooks.orders_service.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String demoUsername = "sahan";
    private final String demoPassword = "Password123";

    // ✅ FIXED: constant key (DO NOT CHANGE THIS STRING)
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12";

    public static SecretKey getJwtKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public static class AuthRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) { this.token = token; }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        if (!demoUsername.equals(request.getUsername()) ||
            !demoPassword.equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = Jwts.builder()
                .setSubject(request.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getJwtKey())
                .compact();

        return new AuthResponse(token);
    }
}