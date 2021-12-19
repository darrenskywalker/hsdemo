package com.example.hsdemo.security.jwt.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUtils {

    @Value("${hsdemo.app.jwtSecret}")
    private String jwtSecret;

    @Value("${hsdemo.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken() {
        return Jwts.builder()
                .setSubject("public")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
