package woowacourse.hanglog.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import woowacourse.hanglog.member.application.MemberService;
import woowacourse.hanglog.member.domain.Session;
import woowacourse.hanglog.web.security.token.TokenProcessor;

class LoginFilter extends AuthenticationProcessingFilter {

    private final MemberService memberService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected LoginFilter(TokenProcessor tokenProcessor, MemberService memberService) {
        super("/api/v1/auth/login", tokenProcessor);
        this.memberService = memberService;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            Session session = memberService.login(loginRequest.socialId(), loginRequest.nickname(), loginRequest.imageUrl());

            return new UsernamePasswordAuthenticationToken(session, null, null);
        } catch (Exception ignored) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private record LoginRequest(String socialId, String nickname, String imageUrl) {

    }

}
