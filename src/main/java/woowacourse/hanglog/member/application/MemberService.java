package woowacourse.hanglog.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.member.domain.Member;
import woowacourse.hanglog.member.domain.Session;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberReader memberReader;
    private final SessionReader sessionReader;
    private final MemberProcessor memberProcessor;
    private final SessionProcessor sessionProcessor;

    public void checkExistMember(Long memberId) {
        memberReader.checkExistMember(memberId);
    }

    public Member getMember(Long memberId) {
        return memberReader.getMemberById(memberId);
    }

    public Session getSession(String sessionId) {
        return sessionReader.getSessionById(sessionId);
    }

    public Session login(String socialId, String nickname, String imageUrl) {
        Member loginedMember = memberProcessor.login(socialId, nickname, imageUrl);
        return sessionProcessor.createSession(loginedMember.id());
    }

    public void logout(Long memberId) {
        sessionProcessor.deleteSessionByMemberId(memberId);
    }

    public void updateMember(Long memberId, String nickname, String imageUrl) {
        memberProcessor.updateMember(memberId, nickname, imageUrl);
    }

    public void deleteMember(Long memberId) {
        sessionProcessor.deleteSessionByMemberId(memberId);
        memberProcessor.deleteMemberById(memberId);
    }

}
