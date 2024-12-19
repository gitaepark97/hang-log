package woowacourse.hanglog.web.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
class MemberController {

    private final MemberFacade memberFacade;

    @GetMapping
    public MyPageResponse getMyInfo(@AuthenticationPrincipal Long memberId) {
        return memberFacade.getMember(memberId);
    }

    @PutMapping
    public void updateMyInfo(
        @AuthenticationPrincipal Long memberId,
        @RequestBody @Valid MyPageRequest request
    ) {
        memberFacade.updateMember(memberId, request);
    }

}
