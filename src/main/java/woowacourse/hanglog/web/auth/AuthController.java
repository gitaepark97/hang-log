package woowacourse.hanglog.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/logout")
    void logout(@AuthenticationPrincipal Long memberId) {
        authFacade.logout(memberId);
    }

    @DeleteMapping("/withdraw")
    void withdraw(@AuthenticationPrincipal Long memberId) {
        authFacade.withdraw(memberId);
    }

}
