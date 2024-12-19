package woowacourse.hanglog.web.security.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.domain.Session;
import woowacourse.hanglog.support.provider.TokenProvider;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class TokenProcessor {

    private static final String MEMBER_ID = "memberId";
    private static final String SESSION_ID = "sessionId";

    private static final Duration ACCESS_EXPIRATION = Duration.ofHours(1);
    private static final Duration REFRESH_EXPIRATION = Duration.ofDays(7);

    private final TokenProvider tokenProvider;

    public AuthToken issueAuthToken(Session session) {
        String accessToken = tokenProvider.issueToken(Map.of(MEMBER_ID, session.memberId()), ACCESS_EXPIRATION);
        String refreshToken = tokenProvider.issueToken(Map.of(SESSION_ID, session.id()), REFRESH_EXPIRATION);
        return new AuthToken(accessToken, refreshToken);
    }

    public Long extractMemberId(String accessToken) {
        return Long.parseLong(tokenProvider.parseToken(accessToken).get(MEMBER_ID).toString());
    }

    public String extractSessionId(String refreshToken) {
        return tokenProvider.parseToken(refreshToken).get(SESSION_ID).toString();
    }

}
