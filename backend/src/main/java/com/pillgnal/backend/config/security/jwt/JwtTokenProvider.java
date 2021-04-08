package com.pillgnal.backend.config.security.jwt;

import com.pillgnal.backend.config.security.UserPrincipal;
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
    String authTokenKey;

    @Value("${auth.tokenExpirationMsec}")
    String tokenExpirationMsec;

    @Value("${auth.refrashTokenExpirationMsec}")
    String refrashTokenExpirationMsec;

    // JWT 토큰 생성
    public String createAccessToken(UserPrincipal userPrincipal) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +
                Integer.valueOf(tokenExpirationMsec));
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, authTokenKey)
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
                .signWith(SignatureAlgorithm.HS512, authTokenKey)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(authTokenKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(authTokenKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            System.out.println("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }
}