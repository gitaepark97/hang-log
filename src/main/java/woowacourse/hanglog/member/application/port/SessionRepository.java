package woowacourse.hanglog.member.application.port;

import woowacourse.hanglog.member.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findById(String id);

    Session save(Session session);

    void deleteByMemberId(Long memberId);

}
