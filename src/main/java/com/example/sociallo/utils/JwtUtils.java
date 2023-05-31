package com.example.sociallo.utils;

import com.example.sociallo.security.user.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import static com.example.sociallo.constants.Messages.*;

@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.sign-in.secret-key}")
    private String jwtSecret;

    @Value("${jwt.exp.date}")
    private long jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] decoded = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(decoded);
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt);
            return !isTokenExpired(jwt);
        } catch (MalformedJwtException e) {
            logger.error(INVALID_JWT, e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(JWT_EXPIRED, e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(JWT_UNSUPPORTED, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(JWT_EMPTY, e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(jwt)
                .getBody().getSubject();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts
                .parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(token).getBody().getExpiration();

        return new Date().after(expiration);
    }


}
