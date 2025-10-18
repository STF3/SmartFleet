package com.smartfleet.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    private final static String SECRET_KEY="superstrongsecretkeythatshouldbeatleast32chars";
    private Key getSigingKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
