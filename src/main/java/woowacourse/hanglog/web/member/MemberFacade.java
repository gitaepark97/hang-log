package woowacourse.hanglog.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.MemberService;

@RequiredArgsConstructor
@Component
class MemberFacade {

    private final MemberService memberService;

    MyPageResponse getMember(Long memberId) {
        return MyPageResponse.from(memberService.getMember(memberId));
    }

    void updateMember(Long memberId, MyPageRequest request) {
        memberService.updateMember(memberId, request.nickname(), request.imageUrl());
    }

}
