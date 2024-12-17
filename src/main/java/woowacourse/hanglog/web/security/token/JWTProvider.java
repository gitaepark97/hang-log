package woowacourse.hanglog.web.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.domain.Session;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
class JWTProvider implements TokenProvider {

    private static final String MEMBER_ID = "memberId";
    private static final String SESSION_ID = "sessionId";

    private static final Duration ACCESS_EXPIRATION = Duration.ofHours(1);
    private static final Duration REFRESH_EXPIRATION = Duration.ofDays(7);

    private final SecretKey secretKey;

    JWTProvider(@Value("${jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key()
            .build()
            .getAlgorithm());
    }

    @Override
    public AuthToken issueAuthToken(Session session) {
        String accessToken = issueToken(Map.of(MEMBER_ID, session.memberId()), ACCESS_EXPIRATION);
        String refreshToken = issueToken(Map.of(SESSION_ID, session.id()), REFRESH_EXPIRATION);
        return new AuthToken(accessToken, refreshToken);
    }

    @Override
    public Long extractMemberId(String accessToken) {
        return parseToken(accessToken).get(MEMBER_ID, Long.class);
    }

    @Override
    public String extractSessionId(String refreshToken) {
        return parseToken(refreshToken).get(SESSION_ID, String.class);
    }

    private String issueToken(Map<String, Object> claims, Duration expiration) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(expiration);
        return Jwts.builder()
            .claims(claims)
            .issuedAt(Date.from(issuedAt))
            .expiration(Date.from(expiresAt))
            .signWith(secretKey)
            .compact();
    }

    private Claims parseToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

}
