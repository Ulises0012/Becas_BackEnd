package sv.org.arrupe.API_BackEnd.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final SecretKey jwtSigningKey;
    private final int jwtExpirationInMs;

    public JwtTokenProvider(@Autowired SecretKey jwtSigningKey, 
                            @Value("${app.jwtExpirationInMs}") int jwtExpirationInMs) {
        this.jwtSigningKey = jwtSigningKey;
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getCarnet())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtSigningKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getCarnetFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSigningKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtSigningKey)
                .build()
                .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            logger.error("JWT validation failed: {}", ex.getMessage());
        }
        return false;
    }
}
