package com.Adily.Project.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Service

public class jwtservice {


    private String SECRET;

    @PostConstruct
    private void init() {
        SECRET = generateSecretKey();
    }

    private String generateSecretKey() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return io.jsonwebtoken.io.Encoders.BASE64.encode(key.getEncoded());
    }


    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles); // store roles in token

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }


    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object roles = claims.get("roles");
        if (roles instanceof List<?>) {
            List<String> roleList = new ArrayList<>();
            for (Object r : (List<?>) roles) {
                roleList.add(String.valueOf(r));
            }
            return roleList;
        }
        return new ArrayList<>();
    }


    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
