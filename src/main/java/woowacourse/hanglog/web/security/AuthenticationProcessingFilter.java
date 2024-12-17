package woowacourse.hanglog.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import woowacourse.hanglog.core.domain.Session;
import woowacourse.hanglog.core.exception.ErrorCode;
import woowacourse.hanglog.web.ApiResponse;
import woowacourse.hanglog.web.security.token.AuthToken;
import woowacourse.hanglog.web.security.token.TokenProvider;

import java.io.IOException;
import java.util.Map;

abstract class AuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    protected final TokenProvider tokenProvider;

    protected AuthenticationProcessingFilter(String defaultFilterProcessesUrl, TokenProvider tokenProvider) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        return null;
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) throws IOException {
        Session session = (Session) authResult.getPrincipal();

        AuthToken authToken = tokenProvider.issueAuthToken(session);

        setSuccessResponse(response, authToken);
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed
    ) throws IOException {
        AuthenticationHandler.setResponse(response, ApiResponse.fail(ErrorCode.UNAUTHORIZED.toException()));
    }

    protected void setSuccessResponse(HttpServletResponse response, AuthToken authToken) throws IOException {
        response.addCookie(createRefreshTokenCookie(authToken.refresh()));
        AuthenticationHandler.setResponse(response, ApiResponse.of(HttpStatus.OK, Map.of("accessToken", authToken.access())));
    }

    private Cookie createRefreshTokenCookie(String value) {
        Cookie cookie = new Cookie("refreshToken", value);

        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        return cookie;
    }

}
