package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.domain.Member;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberReader memberReader;
    private final MemberProcessor memberProcessor;

    public Member getMember(Long memberId) {
        return memberReader.getMemberById(memberId);
    }

    public void updateMember(Long memberId, String nickname, String imageUrl) {
        memberProcessor.updateMember(memberId, nickname, imageUrl);
    }

}
