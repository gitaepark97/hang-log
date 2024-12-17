package woowacourse.hanglog.web.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import woowacourse.hanglog.core.application.AuthService;
import woowacourse.hanglog.core.domain.Session;
import woowacourse.hanglog.web.security.token.TokenProvider;

import java.util.Optional;

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
        Optional<String> refreshToken = extractRefreshToken(request);
        if (refreshToken.isEmpty()) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        try {
            String sessionId = tokenProvider.extractSessionId(refreshToken.get());
            Session session = authService.getSession(sessionId);

            return new UsernamePasswordAuthenticationToken(session, null, null);
        } catch (Exception ignored) {
            throw new BadCredentialsException("Invalid refresh token");
        }
    }

    private Optional<String> extractRefreshToken(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return Optional.of(cookie.getValue());
            }
        }
        return Optional.empty();
    }

}
