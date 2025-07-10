package kdt.minone.global.config.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
public class JwtConfig {

    private final String secret = "mySecretKeyForJWTSigningThatShouldBeLongEnoughForHMACAlgorithmAndMustBeAtLeast32BytesLong";
    private long accessTokenExpiration = 30 * 60 * 1000L; // 30분
    private long refreshTokenExpiration = 7 * 24 * 60 * 60 * 1000L; // 7일
    private final String issuer = "minone-backend";
    private final String headerName = "Authorization";
    private final String tokenPrefix = "Bearer ";

    public String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        }
        return null;
    }

    public void setAccessTokenExpirationMinutes(int minutes) {
        this.accessTokenExpiration = minutes * 60 * 1000L;
    }

    public void setRefreshTokenExpirationDays(int days) {
        this.refreshTokenExpiration = days * 24 * 60 * 60 * 1000L;
    }

    public long getAccessTokenExpirationMinutes() {
        return accessTokenExpiration / (60 * 1000L);
    }

    public long getRefreshTokenExpirationDays() {
        return refreshTokenExpiration / (24 * 60 * 60 * 1000L);
    }
}