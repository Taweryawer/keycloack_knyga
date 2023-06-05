package util;

import domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final Logger log = LogManager.getLogger(JwtUtil.class);

    public String generateJwt(User user) {
        String jws = Jwts.builder()
            .setIssuer("TestAngularApp")
            .setSubject(user.getLogin())
            .claim("name", user.getFirstName())
            .claim("role", user.getRole().getName())
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
            .signWith(
                Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)),
                SignatureAlgorithm.HS256
            )
            .compact();

        return jws;
    }

    public boolean validateJwt(String jwt) {
        try {
            Jwts.parserBuilder().requireIssuer("TestAngularApp")
                .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
                .build().parseClaimsJws(jwt);
        } catch (SignatureException e) {
            log.warn("Wrong JWT {}. Reason: {}", jwt, e.getMessage());
            return false;
        }

        return true;
    }

    public String getUsername(String jwt) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
            .build()
            .parseClaimsJws(jwt)
            .getBody()
            .getSubject();
    }
}
