package woowacourse.hanglog.support.provider;

import java.time.Duration;
import java.util.Map;

public interface TokenProvider {

    String issueToken(Map<String, Object> claims, Duration expiration);

    Map<String, Object> parseToken(String token);

}
