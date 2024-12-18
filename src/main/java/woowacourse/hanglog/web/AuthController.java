package woowacourse.hanglog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.hanglog.core.application.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private final AuthService authService;

    @PostMapping("/logout")
    void logout(@AuthenticationPrincipal Long memberId) {
        authService.logout(memberId);
    }

    @DeleteMapping("/withdraw")
    void withdraw(@AuthenticationPrincipal Long memberId) {
        authService.withdraw(memberId);
    }

}
