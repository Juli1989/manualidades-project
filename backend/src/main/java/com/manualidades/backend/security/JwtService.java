package com.manualidades.backend.security;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(
            String email
    ) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )

                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }
}