package woowacourse.hanglog.member.infrastructure;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.member.application.port.SessionRepository;
import woowacourse.hanglog.member.domain.Session;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class SessionRepositoryImpl implements SessionRepository {

    private final SessionEntityRepository sessionEntityRepository;

    @Override
    public Optional<Session> findById(String id) {
        return sessionEntityRepository.findById(id).map(SessionEntity::toSession);
    }

    @Override
    public Session save(Session session) {
        return sessionEntityRepository.save(SessionEntity.from(session)).toSession();
    }

    @Transactional
    @Override
    public void deleteByMemberId(Long memberId) {
        sessionEntityRepository.deleteByMemberId(memberId);
        sessionEntityRepository.flush();
    }

}
