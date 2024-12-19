package woowacourse.hanglog.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.port.MemberRepository;
import woowacourse.hanglog.support.aop.Retry;
import woowacourse.hanglog.support.exception.ErrorCode;
import woowacourse.hanglog.support.provider.RandomProvider;

@RequiredArgsConstructor
@Component
class NicknameGenerator {

    private static final int DIGIT_CODE_LENGTH = 4;

    private final RandomProvider randomProvider;
    private final MemberRepository memberRepository;

    @Retry(5)
    String generateUniqueNickname(String nickname) {
        String nicknameWithDigitCode = nickname + generateRandomDigitCode();
        checkUniqueNickname(nicknameWithDigitCode);
        return nicknameWithDigitCode;
    }

    private void checkUniqueNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw ErrorCode.DUPLICATED_MEMBER_NICKNAME.toException();
        }
    }

    private String generateRandomDigitCode() {
        int randomNumber = randomProvider.randomInt(0, (int) Math.pow(10, DIGIT_CODE_LENGTH) - 1);
        return String.format("%04d", randomNumber);
    }

}
