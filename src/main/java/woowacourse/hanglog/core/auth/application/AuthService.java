package woowacourse.hanglog.core.auth.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.auth.domain.Session;
import woowacourse.hanglog.core.member.application.MemberProcessor;
import woowacourse.hanglog.core.member.domain.Member;

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

    @Transactional
    public void withdraw(Long memberId) {
        sessionProcessor.deleteSession(memberId);
        memberProcessor.deleteMember(memberId);
    }

}