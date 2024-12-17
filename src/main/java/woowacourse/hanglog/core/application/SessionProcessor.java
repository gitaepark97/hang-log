package woowacourse.hanglog.core.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.ClockProvider;
import woowacourse.hanglog.core.application.port.SessionRepository;
import woowacourse.hanglog.core.application.port.UUIDProvider;
import woowacourse.hanglog.core.domain.Session;

@RequiredArgsConstructor
@Component
class SessionProcessor {

    private final UUIDProvider uuidProvider;
    private final ClockProvider clockProvider;
    private final SessionRepository sessionRepository;

    @Transactional
    Session createSession(Long memberId) {
        deleteSession(memberId);
        Session newSession = Session.of(uuidProvider.generateRandomUUID(), memberId, clockProvider.millis());
        return sessionRepository.save(newSession);
    }
    
    void deleteSession(Long memberId) {
        sessionRepository.deleteByMemberId(memberId);
    }

}
