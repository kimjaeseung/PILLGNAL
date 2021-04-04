package com.pillgnal.backend.config.oauth2.jwt;

import com.pillgnal.backend.config.oauth2.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {
    @Value("${auth.tokenSecret}")
    String authToken;

    @Value("${auth.tokenExpirationMsec}")
    String tokenExpirationMsec;

    @Value("${auth.refrashTokenExpirationMsec}")
    String refrashTokenExpirationMsec;

    // JWT 토큰 생성
    public String createAccessToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +
                Integer.valueOf(tokenExpirationMsec));
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, authToken)
                .compact();
    }

    // Refrash JWT 토큰 생성
    public String createRefreshToken(String value) {
        Claims claims = Jwts.claims();
        claims.put("value", value);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Integer.valueOf(refrashTokenExpirationMsec));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, authToken)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(authToken)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(authToken).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}