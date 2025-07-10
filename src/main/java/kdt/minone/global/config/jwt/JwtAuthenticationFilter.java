package kdt.minone.global.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kdt.minone.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            // 요청에서 JWT 토큰 추출
            String token = jwtConfig.extractToken(bearerToken);

            // 토큰이 유효한 경우 SecurityContext에 인증 정보 설정
            if (token != null && jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.debug("JWT 인증 성공: " + authentication.getName());
            }
        } catch (Exception e) {
            // JWT 처리 중 예외 발생 시 SecurityContext 클리어
            SecurityContextHolder.clearContext();
            logger.error("JWT 인증 실패: " + e.getMessage());

            return;
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
