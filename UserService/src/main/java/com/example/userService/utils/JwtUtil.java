package com.example.userService.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expiryDurationInMin}")
    private long expiryDurationInMin;
    public String generateToken(String username, Map<String, Object> claims){
        Date expiration = new Date(System.currentTimeMillis() + (expiryDurationInMin *60*1000));
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .issuedAt(new Date())
                .expiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public String extractUsername(String token){
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getPayload().getSubject();
    }
    public int extractUserId(String token){
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("userId", Integer.class);
    }
    public Date extractExpiry(String token){
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getExpiration();
    }

    public boolean isActiveToken(String token){
        return extractExpiry(token).after(new Date());
    }
}
