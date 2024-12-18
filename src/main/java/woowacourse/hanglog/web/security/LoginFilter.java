package woowacourse.hanglog.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import woowacourse.hanglog.core.auth.application.AuthService;
import woowacourse.hanglog.core.auth.domain.Session;
import woowacourse.hanglog.web.security.token.TokenProvider;

class LoginFilter extends AuthenticationProcessingFilter {

    private final AuthService authService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected LoginFilter(TokenProvider tokenProvider, AuthService authService) {
        super("/api/v1/auth/login", tokenProvider);
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            Session session = authService.login(loginRequest.socialId(), loginRequest.nickname(), loginRequest.imageUrl());

            return new UsernamePasswordAuthenticationToken(session, null, null);
        } catch (Exception ignored) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private record LoginRequest(String socialId, String nickname, String imageUrl) {

    }

}
