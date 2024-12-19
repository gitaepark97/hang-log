package woowacourse.hanglog.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.MemberService;

@Component
@RequiredArgsConstructor
class AuthFacade {

    private final MemberService memberService;

    void logout(Long memberId) {
        memberService.logout(memberId);
    }

    void withdraw(Long memberId) {
        memberService.deleteMember(memberId);
    }

}
