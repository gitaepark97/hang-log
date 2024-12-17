package woowacourse.hanglog.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import woowacourse.hanglog.web.security.token.TokenProvider;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AccessTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<String> accessToken = obtainAccessToken(request);
        if (accessToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Long userId = tokenProvider.extractMemberId(accessToken.get());

            Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception ignored) {
            throw new BadCredentialsException("Invalid access token");
        }
    }

    private Optional<String> obtainAccessToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith(BEARER)) {
            return Optional.empty();
        }
        return Optional.of(authHeader.substring(BEARER.length()));
    }

}
