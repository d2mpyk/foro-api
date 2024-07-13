package com.d2mp.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.d2mp.foro.domain.model.Usuario;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value(value = "$(api.security.secret)")
    private String JWT_Secret;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_Secret);
            return JWT.create()
                    .withIssuer("foro-hub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new IntegrityCheck("Ocurrió un error en la generación del token");
        }
    }

    private Instant generarExpiracion(){
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone = ZoneId.of("America/Bogota");
        ZoneOffset zoneOffSet = zone.getRules().getOffset(now);
        return LocalDateTime.now().plusMinutes(20).toInstant(zoneOffSet);
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_Secret);
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("API Foro Hub")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new IntegrityCheck("¡Verificación inválida!");
        }
    }

    public boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_Secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("API Foro Hub")
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            return false;
        }
    }
}
