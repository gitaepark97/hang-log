package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.MemberRepository;
import woowacourse.hanglog.core.domain.Member;
import woowacourse.hanglog.core.exception.ErrorCode;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class MemberReader {

    private final MemberRepository memberRepository;

    void checkExistMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw ErrorCode.NOT_FOUND_USER.toException();
        }
    }

    Optional<Member> findMemberBySocialId(String socialId) {
        return memberRepository.findBySocialId(socialId);
    }

    Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_USER::toException);
    }

}
