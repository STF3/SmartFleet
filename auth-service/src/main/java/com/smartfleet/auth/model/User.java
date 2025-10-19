package com.smartfleet.auth.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private String role;
}
