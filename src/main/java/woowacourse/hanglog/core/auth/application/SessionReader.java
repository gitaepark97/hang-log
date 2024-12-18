package woowacourse.hanglog.core.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.auth.application.port.SessionRepository;
import woowacourse.hanglog.core.auth.domain.Session;
import woowacourse.hanglog.core.exception.ErrorCode;

@RequiredArgsConstructor
@Component
class SessionReader {

    private final SessionRepository sessionRepository;

    Session getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(ErrorCode.UNAUTHORIZED::toException);
    }

}
