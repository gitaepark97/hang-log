package woowacourse.hanglog.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.port.MemberRepository;
import woowacourse.hanglog.member.application.port.SessionRepository;
import woowacourse.hanglog.member.domain.Member;
import woowacourse.hanglog.support.exception.ErrorCode;

@RequiredArgsConstructor
@Component
class MemberReader {

    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;

    void checkExistMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw ErrorCode.NOT_FOUND_MEMBER.toException();
        }
    }

    Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_MEMBER::toException);
    }

}
