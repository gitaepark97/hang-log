package woowacourse.hanglog.core.auth.application.port;

import woowacourse.hanglog.core.auth.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findById(String id);

    Session save(Session session);

    void deleteByMemberId(Long memberId);

}
