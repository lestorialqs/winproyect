package com.dbp.winproyect.auth.service;

import com.dbp.winproyect.appuser.domain.AppUser;
import org.springframework.security.core.GrantedAuthority;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    // Clave secreta para firmar los tokens JWT (puedes cambiarla por algo más seguro)
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    // Generar el token JWT usando el UserDetails del usuario
    public String generateToken(AppUser appUser) {
        // Extraemos el email del usuario
        String username = appUser.getEmail();

        // Extraemos el rol (Client, Enterprise, Freelancer)
        String role = appUser.getRole().name();  // Obtenemos el nombre del rol (CLIENT, ENTERPRISE, FREELANCE)

        // Crear los claims (cualquier información adicional que quieras agregar al token)
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);  // Sin el prefijo ROLE_
        // Añadir el prefijo ROLE_ al rol
        // Incluimos el rol en los claims



        // Crear el token JWT
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)  // El email o nombre de usuario
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Expira en 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Crear el token con claims (opcional) y el subject (que será el nombre de usuario)
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de validez
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Asegúrate que SECRET_KEY no tenga caracteres inválidos
                .compact();
    }

    // Validar el token: verificar que el token no esté expirado y que el nombre de usuario coincida
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Verificar si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer cualquier claim del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}