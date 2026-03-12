package es.dwes.backend_usuarios.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import es.dwes.backend_usuarios.DTO.UsuarioDTO;

@Component
public class JwtService {
private final String SECRET_KEY = "z8pX9vW2mL5qR7tY4uI1oP0aS3dF6gH9jK8lZ7xC5vB4nN2mQ1wE";

    public String crearToken(UsuarioDTO usuario) {
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("username", usuario.getNombreUsuario())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
