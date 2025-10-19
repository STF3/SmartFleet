package com.smartfleet.auth.model;

import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RefreshToken {
    private Long id;
    private String token;
    private Long userId;
    private Instant expiryDate;
}
