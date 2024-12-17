package woowacourse.hanglog.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import woowacourse.hanglog.core.application.MemberService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
class MemberController {

    private final MemberService memberService;

    @GetMapping
    public MemberResponse.MyPage getMyInfo(@AuthenticationPrincipal Long memberId) {
        return MemberResponse.MyPage.from(memberService.getMember(memberId));
    }

    @PutMapping
    public void updateMyInfo(@AuthenticationPrincipal Long memberId, @RequestBody @Valid MemberRequest.MyPage request) {
        memberService.updateMember(memberId, request.nickname(), request.imageUrl());
    }

}
