package woowacourse.hanglog.web.security.token;

import woowacourse.hanglog.core.domain.Session;

public interface TokenProvider {

    AuthToken issueAuthToken(Session session);

    Long extractMemberId(String accessToken);

    String extractSessionId(String refreshToken);

}
