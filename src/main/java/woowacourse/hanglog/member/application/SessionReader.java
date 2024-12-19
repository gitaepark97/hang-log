package woowacourse.hanglog.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.port.SessionRepository;
import woowacourse.hanglog.member.domain.Session;
import woowacourse.hanglog.support.exception.ErrorCode;

@RequiredArgsConstructor
@Component
class SessionReader {

    private final SessionRepository sessionRepository;

    Session getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(ErrorCode.UNAUTHORIZED::toException);
    }

}
