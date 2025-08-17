package com.foro.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationSeconds;

    public String generarToken(String username) {
        Algorithm alg = Algorithm.HMAC256(secret);
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(expirationSeconds, ChronoUnit.SECONDS)))
                .sign(alg);
    }

    public String validarTokenYObtenerUsuario(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        DecodedJWT decoded = JWT.require(alg).build().verify(token);
        return decoded.getSubject();
    }
}
