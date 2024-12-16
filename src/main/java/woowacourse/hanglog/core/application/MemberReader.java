package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.MemberRepository;
import woowacourse.hanglog.core.domain.Member;
import woowacourse.hanglog.core.exception.ApplicationException;
import woowacourse.hanglog.core.exception.ErrorCode;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class MemberReader {

    private final MemberRepository memberRepository;

    Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    Optional<Member> findMemberBySocialId(String socialId) {
        return memberRepository.findBySocialId(socialId);
    }

}
