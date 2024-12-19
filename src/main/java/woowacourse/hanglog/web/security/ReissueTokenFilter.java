package woowacourse.hanglog.web.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import woowacourse.hanglog.member.application.MemberService;
import woowacourse.hanglog.member.domain.Session;
import woowacourse.hanglog.web.security.token.TokenProcessor;

class ReissueTokenFilter extends AuthenticationProcessingFilter {

    private final MemberService memberService;

    protected ReissueTokenFilter(TokenProcessor tokenProcessor, MemberService memberService) {
        super("/api/v1/auth/reissue-token", tokenProcessor);
        this.memberService = memberService;
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
            String sessionId = tokenProcessor.extractSessionId(refreshToken);
            Session session = memberService.getSession(sessionId);

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
