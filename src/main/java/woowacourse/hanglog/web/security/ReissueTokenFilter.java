package woowacourse.hanglog.web.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import woowacourse.hanglog.core.auth.application.AuthService;
import woowacourse.hanglog.core.auth.domain.Session;
import woowacourse.hanglog.web.security.token.TokenProvider;

class ReissueTokenFilter extends AuthenticationProcessingFilter {

    private final AuthService authService;

    protected ReissueTokenFilter(TokenProvider tokenProvider, AuthService authService) {
        super("/api/v1/auth/reissue-token", tokenProvider);
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        String refreshToken = extractRefreshToken(request);
        if (refreshToken == null) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        try {
            String sessionId = tokenProvider.extractSessionId(refreshToken);
            Session session = authService.getSession(sessionId);

            return new UsernamePasswordAuthenticationToken(session, null, null);
        } catch (Exception ignored) {
            throw new BadCredentialsException("Invalid refresh token");
        }
    }

    private String extractRefreshToken(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
