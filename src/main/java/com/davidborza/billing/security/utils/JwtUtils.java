package com.davidborza.billing.security.utils;

import com.davidborza.billing.security.config.JwtConfiguration;
import com.davidborza.billing.security.model.SecurityUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Component
@AllArgsConstructor
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    private final JwtConfiguration jwtConfiguration;

    /**
     * Generate the JWT token.
     *
     * @param userPrincipal The given SecurityUser object.
     * @return JWT token.
     */
    public String generateJwtToken(final SecurityUser userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    /**
     * Generate JWT token from the username.
     *
     * @param username The given username.
     * @return The JWT token.
     */
    public String generateTokenFromUsername(final String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfiguration.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getJwtSecret())
                .compact();
    }

    /**
     * Generate JWT refresh token from the username.
     *
     * @param username The given username.
     * @return The JWT refresh token.
     */
    public String generateRefreshTokenFromUsername(final String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfiguration.getJwtRefreshExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getJwtSecret())
                .compact();
    }

    /**
     * Get the username from the JWT token.
     *
     * @param token The given JWT token.
     * @return The username.
     */
    public String getUserNameFromJwtToken(final String token) {
        return Jwts.parser().setSigningKey(jwtConfiguration.getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * JWT token validation.
     *
     * @param accessToken The given accessToken.
     * @return JWT token is valid or not.
     */
    @SuppressWarnings("checkstyle:ReturnCount")
    public boolean validateJwtToken(final String accessToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfiguration.getJwtSecret()).parseClaimsJws(accessToken);
            return true;
        } catch (final SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (final MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (final ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (final UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (final IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
