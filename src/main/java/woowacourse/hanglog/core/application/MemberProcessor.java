package woowacourse.hanglog.core.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.ClockProvider;
import woowacourse.hanglog.core.application.port.MemberRepository;
import woowacourse.hanglog.core.application.port.RandomProvider;
import woowacourse.hanglog.core.domain.Member;
import woowacourse.hanglog.core.exception.ApplicationException;
import woowacourse.hanglog.core.exception.ErrorCode;

@RequiredArgsConstructor
@Component
class MemberProcessor {

    private static final int MAX_TRY_COUNT = 5;
    private static final int DIGIT_CODE_LENGTH = 4;

    private final ClockProvider clockProvider;
    private final RandomProvider randomProvider;
    private final MemberRepository memberRepository;

    @Transactional
    Member createMember(String socialId, String nickname, String imageUrl) {
        String uniqueNickname = generateUniqueNickname(nickname);
        Member newMember = Member.of(socialId, uniqueNickname, imageUrl, clockProvider.millis());
        return memberRepository.save(newMember);
    }

    @Transactional
    void updateMember(Long memberId, String nickname, String imageUrl) {
        Member existedMember = memberRepository.findById(memberId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));

        if (!existedMember.isSameNickname(nickname)) {
            checkUniqueNickname(nickname);
        }
        Member updatedMember = existedMember.update(nickname, imageUrl, clockProvider.millis());
        memberRepository.save(updatedMember);
    }

    private void checkUniqueNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ApplicationException(ErrorCode.DUPLICATED_MEMBER_NICKNAME);
        }
    }

    private String generateUniqueNickname(String nickname) {
        int tryCount = 0;

        while (tryCount < MAX_TRY_COUNT) {
            String nicknameWithDigitCode = nickname + generateRandomDigitCode();
            if (!memberRepository.existsByNickname(nicknameWithDigitCode)) {
                return nicknameWithDigitCode;
            }
            tryCount++;
        }

        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private String generateRandomDigitCode() {
        int randomNumber = randomProvider.randomInt(0, (int) Math.pow(10, DIGIT_CODE_LENGTH) - 1);
        return String.format("%04d", randomNumber);
    }

}
