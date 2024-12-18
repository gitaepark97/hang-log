package woowacourse.hanglog.core.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.exception.ErrorCode;
import woowacourse.hanglog.core.member.application.port.MemberRepository;
import woowacourse.hanglog.core.member.domain.Member;

@RequiredArgsConstructor
@Component
public class MemberReader {

    private final MemberRepository memberRepository;

    public void checkExistMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw ErrorCode.NOT_FOUND_MEMBER.toException();
        }
    }

    Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_MEMBER::toException);
    }

}
