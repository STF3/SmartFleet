package com.smartfleet.auth.service;

import com.smartfleet.auth.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    void register_shouldSaveUserAndReturnTokens() {
        User user = User.builder()
                .email("test@mail.com")
                .password("pass123")
                .role("ADMIN")
                .build();

        Map<String, String> tokens = authService.register(user);

        assertThat(tokens)
                .containsKeys("accessToken", "refreshToken")
                .allSatisfy((k, v) -> assertThat(v).isNotEmpty());
    }

    @Test
    void login_shouldReturnTokensForValidUser() {
        User user = User.builder()
                .email("login@mail.com")
                .password("pass123")
                .role("DISPATCHER")
                .build();
        authService.register(user);

        Map<String, String> tokens = authService.login("login@mail.com", "pass123");

        assertThat(tokens.get("accessToken")).isNotEmpty();
        assertThat(tokens.get("refreshToken")).isNotEmpty();
    }

    @Test
    void login_shouldFailForInvalidPassword() {
        User user = User.builder()
                .email("bad@mail.com")
                .password("realpass")
                .role("DRIVER")
                .build();
        authService.register(user);

        assertThatThrownBy(() ->
                authService.login("bad@mail.com", "wrong"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Invalid credentials");
    }
}
