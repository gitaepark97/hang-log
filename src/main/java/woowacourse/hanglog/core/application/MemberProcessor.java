package woowacourse.hanglog.core.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.ClockProvider;
import woowacourse.hanglog.core.application.port.MemberRepository;
import woowacourse.hanglog.core.application.port.RandomProvider;
import woowacourse.hanglog.core.aspect.Retry;
import woowacourse.hanglog.core.domain.Member;
import woowacourse.hanglog.core.exception.ErrorCode;

@RequiredArgsConstructor
@Component
public class MemberProcessor {

    private static final int DIGIT_CODE_LENGTH = 4;


    private final ClockProvider clockProvider;
    private final RandomProvider randomProvider;
    private final MemberRepository memberRepository;

    @Retry(5)
    @Transactional
    Member createMember(String socialId, String nickname, String imageUrl) {
        String uniqueNickname = generateUniqueNickname(nickname);
        Member newMember = Member.of(socialId, uniqueNickname, imageUrl, clockProvider.millis());
        return memberRepository.save(newMember);
    }

    @Transactional
    void updateMember(Long memberId, String nickname, String imageUrl) {
        Member existMember = memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_USER::toException);

        if (!existMember.isSameNickname(nickname)) {
            checkUniqueNickname(nickname);
        }
        Member updatedMember = existMember.update(nickname, imageUrl, clockProvider.millis());
        memberRepository.save(updatedMember);
    }

    private String generateUniqueNickname(String nickname) {
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
