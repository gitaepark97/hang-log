package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.SessionRepository;
import woowacourse.hanglog.core.domain.Session;
import woowacourse.hanglog.core.exception.ErrorCode;

@RequiredArgsConstructor
@Component
class SessionReader {

    private final SessionRepository sessionRepository;

    Session getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(ErrorCode.UNAUTHORIZED::toException);
    }

}
