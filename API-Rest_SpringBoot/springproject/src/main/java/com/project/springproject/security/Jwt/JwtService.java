package com.project.springproject.security.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * This class provides methods for generating and validating JSON Web Tokens
 * (JWT) for authentication purposes.
 */
@Service
public class JwtService {

    /**
     * The secret key used for signing the JWT.
     */
    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    /**
     * Generates a JWT token for the specified user.
     *
     * @param user The user details.
     * @return The generated JWT token.
     */
    public String getToken(UserDetails user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDetails cannot be null");
        }
        return getToken(new HashMap<>(), user);
    }

    /**
     * Generates a JWT token with additional claims for the specified user.
     *
     * @param extraClaims Additional claims to include in the JWT.
     * @param user        The user details.
     * @return The generated JWT token.
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Retrieves the username from the specified JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Checks if the specified JWT token is valid for the given user details.
     *
     * @param token       The JWT token.
     * @param userDetails The user details.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Retrieves all the claims from the specified JWT token.
     *
     * @param token The JWT token.
     * @return The claims extracted from the token.
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves a specific claim from the specified JWT token.
     *
     * @param token          The JWT token.
     * @param claimsResolver The function to resolve the desired claim from the
     *                       claims.
     * @param <T>            The type of the claim.
     * @return The resolved claim.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieves the expiration date from the specified JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the specified JWT token has expired.
     *
     * @param token The JWT token.
     * @return True if the token has expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Retrieves the secret key used for signing the JWT.
     *
     * @return The secret key.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
