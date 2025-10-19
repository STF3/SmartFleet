package com.smartfleet.auth.repository;

import com.smartfleet.auth.model.RefreshToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RefreshTokenRepository {

    private final JdbcTemplate jdbc;

    public RefreshTokenRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public RefreshToken create(Long userId) {
        String token = UUID.randomUUID().toString();
        Instant expiry = Instant.now().plusSeconds(24 * 3600);
        jdbc.update("INSERT INTO refresh_tokens (token, user_id, expiry_date) VALUES (?, ?, ?)",
                token, userId, expiry);
        return new RefreshToken(null, token, userId, expiry);
    }

    public Optional<RefreshToken> find(String token) {
        String sql = "SELECT * FROM refresh_tokens WHERE token = ?";
        return jdbc.query(sql, rs -> {
            if (rs.next()) {
                return Optional.of(new RefreshToken(
                        rs.getLong("id"),
                        rs.getString("token"),
                        rs.getLong("user_id"),
                        rs.getTimestamp("expiry_date").toInstant()
                ));
            }
            return Optional.empty();
        }, token);
    }

    public void deleteByUser(Long userId) {
        jdbc.update("DELETE FROM refresh_tokens WHERE user_id = ?", userId);
    }
}
