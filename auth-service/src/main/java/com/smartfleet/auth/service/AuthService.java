package com.smartfleet.auth.service;

import com.smartfleet.auth.model.*;
import com.smartfleet.auth.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final RefreshTokenRepository tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public Map<String, String> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        User saved = userRepo.findByEmail(user.getEmail()).orElseThrow();
        return generateTokens(saved);
    }

    public Map<String, String> login(String email, String password) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");
        return generateTokens(user);
    }

    public Map<String, String> refresh(String token) {
        RefreshToken refresh = tokenRepo.find(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (refresh.getExpiryDate().isBefore(Instant.now()))
            throw new RuntimeException("Token expired");

        User user = userRepo.findByEmail(getEmailFromToken(token)).orElseThrow();
        return generateTokens(user);
    }

    private Map<String, String> generateTokens(User user) {
        String accessToken = jwtService.generateToken(user.getEmail(), Map.of("role", user.getRole()));
        RefreshToken refreshToken = tokenRepo.create(user.getId());
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken.getToken());
    }

    private String getEmailFromToken(String token) {
        return jwtService.extractEmail(token);
    }
}
