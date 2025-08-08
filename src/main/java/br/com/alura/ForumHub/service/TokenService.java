package br.com.alura.ForumHub.service;


import br.com.alura.ForumHub.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API forumHub")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gravar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API forumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }



    private Instant dataExpiracao() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}