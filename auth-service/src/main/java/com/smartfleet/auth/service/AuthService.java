package com.smartfleet.auth.service;

import com.smartfleet.auth.model.*;
import com.smartfleet.auth.repository.RefreshTokenRepository;
import com.smartfleet.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public Map<String, String> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return generateTokens(user);
    }

    public Map<String, String> login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(rawPassword, user.getPassword()))
            throw new RuntimeException("Invalid Credentials");

        return generateTokens(user);
    }

}
