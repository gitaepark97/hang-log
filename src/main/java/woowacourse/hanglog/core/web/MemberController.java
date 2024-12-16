package woowacourse.hanglog.core.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import woowacourse.hanglog.core.application.MemberService;
import woowacourse.hanglog.core.web.request.MyPageRequest;
import woowacourse.hanglog.core.web.response.MyPageResponse;

@RequiredArgsConstructor
@RestController
class MemberController {

    private final MemberService memberService;

    @GetMapping
    public MyPageResponse getMyInfo(@RequestParam Long memberId) {
        return MyPageResponse.from(memberService.getMember(memberId));
    }

    @PutMapping
    public void updateMyInfo(@RequestParam Long memberId, @RequestBody @Valid MyPageRequest request) {
        memberService.updateMember(memberId, request.nickname(), request.imageUrl());
    }

}
