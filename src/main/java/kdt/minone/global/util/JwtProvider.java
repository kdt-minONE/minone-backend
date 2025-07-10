package kdt.minone.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import kdt.minone.global.config.jwt.JwtConfig;
import kdt.minone.global.enums.EmployeeRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {

        String userEmail = getUserEmail(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String createCitizenAccessToken(Long citizenId, String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getAccessTokenExpiration());

        return Jwts.builder()
                .subject(citizenId.toString())
                .claim("email", email)
                .claim("role", "CITIZEN")
                .claim("tokenType", "access")
                .issuer(jwtConfig.getIssuer())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String createEmployeeAccessToken(Long employeeId, String email, EmployeeRole role, Long departmentId) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getAccessTokenExpiration());

        return Jwts.builder()
                .subject(employeeId.toString())
                .claim("email", email)
                .claim("role", role.name())
                .claim("departmentId", departmentId)
                .claim("tokenType", "access")
                .issuer(jwtConfig.getIssuer())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String createRefreshToken(String userRole, Long userId) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getRefreshTokenExpiration());

        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", userRole)
                .claim("tokenType", "refresh")
                .issuer(jwtConfig.getIssuer())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token) {

        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 JWT 토큰", e);
        }
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }

    public String getUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("role", String.class);
    }

    public String getTokenType(String token) {
        Claims claims = getClaims(token);
        return claims.get("tokenType", String.class);
    }

    public Long getDepartmentId(String token) {
        Claims claims = getClaims(token);
        Object departmentId = claims.get("departmentId");
        return departmentId != null ? Long.parseLong(departmentId.toString()) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public boolean isAccessToken(String token) {
        return "access".equals(getTokenType(token));
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(getTokenType(token));
    }

    public long getTimeUntilExpiration(String token) {
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.getTime() - System.currentTimeMillis();
    }
}