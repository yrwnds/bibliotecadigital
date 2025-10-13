package br.csi.bibliotecadigital.infra.security;

import br.csi.bibliotecadigital.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceJWT {

    public String gerarToken(User user) {
        try{
            Algorithm algo = Algorithm.HMAC256("POOW2");
            return JWT.create()
                    .withIssuer("API Avaliar Projetos")
                    .withSubject(user.getUsername())
                    .withClaim("ROLE", user.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algo);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Falha ao gerar token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("POOW2");
            return JWT.require(algorithm)
                    .withIssuer("API Avaliar Projetos")
                    .build()
                    .verify(token)
                    .getSubject();
        }  catch (JWTVerificationException e) {
            throw new RuntimeException("Token invalido ou expirado.");
        }
    }
}
