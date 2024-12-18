package woowacourse.hanglog.core.auth.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.common.ClockProvider;
import woowacourse.hanglog.core.auth.application.port.SessionRepository;
import woowacourse.hanglog.core.auth.application.port.UUIDProvider;
import woowacourse.hanglog.core.auth.domain.Session;

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
