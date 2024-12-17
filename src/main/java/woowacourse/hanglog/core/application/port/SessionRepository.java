package woowacourse.hanglog.core.application.port;

import woowacourse.hanglog.core.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findById(String id);

    Session save(Session session);

    void deleteByMemberId(Long memberId);

}
