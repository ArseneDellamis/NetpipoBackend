package com.netpipo.management.employee_management.UserOps.AuthenticationConfig;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
// second in implementation
@Service
public class JwtService {
//    extracting userName
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

//    method to generate a token
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + AuthConstants.EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    extracting a single claim
    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//extracting token with user details itself
   public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
   }
//    extract All claims from token
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(AuthConstants.SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    method to validate a token
    boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
