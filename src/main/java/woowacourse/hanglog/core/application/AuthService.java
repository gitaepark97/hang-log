package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.domain.Member;
import woowacourse.hanglog.core.domain.Session;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final SessionReader sessionReader;
    private final MemberProcessor memberProcessor;
    private final SessionProcessor sessionProcessor;

    public Session login(String socialId, String nickname, String imageUrl) {
        Member member = memberProcessor.login(socialId, nickname, imageUrl);
        return sessionProcessor.createSession(member.id());
    }

    public Session getSession(String sessionId) {
        return sessionReader.getSessionById(sessionId);
    }

    public void logout(Long memberId) {
        sessionProcessor.deleteSession(memberId);
    }

}
