package com.smartfleet.auth.controller;

import com.smartfleet.auth.model.User;
import com.smartfleet.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> payload) {
        return authService.login(payload.get("email"), payload.get("password"));
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> payload) {
        return authService.refresh(payload.get("refreshToken"));
    }
}
