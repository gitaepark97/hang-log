package woowacourse.hanglog.member.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.port.SessionRepository;
import woowacourse.hanglog.member.domain.Session;
import woowacourse.hanglog.support.provider.ClockProvider;
import woowacourse.hanglog.support.provider.UUIDProvider;

@RequiredArgsConstructor
@Component
class SessionProcessor {

    private final UUIDProvider uuidProvider;
    private final ClockProvider clockProvider;
    private final SessionRepository sessionRepository;

    @Transactional
    Session createSession(Long memberId) {
        deleteSessionByMemberId(memberId);
        Session newSession = Session.of(uuidProvider.generateRandomUUID(), memberId, clockProvider.millis());
        return sessionRepository.save(newSession);
    }

    void deleteSessionByMemberId(Long memberId) {
        sessionRepository.deleteByMemberId(memberId);
    }

}
