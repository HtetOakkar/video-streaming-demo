package com.example.live_stream_demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);

    Claims getClaimsFromToken(String token);
}
