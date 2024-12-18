package woowacourse.hanglog.core.member.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.common.ClockProvider;
import woowacourse.hanglog.core.exception.ErrorCode;
import woowacourse.hanglog.core.member.application.port.MemberRepository;
import woowacourse.hanglog.core.member.domain.Member;

@RequiredArgsConstructor
@Component
public class MemberProcessor {

    private final ClockProvider clockProvider;
    private final MemberRepository memberRepository;
    private final NicknameGenerator nicknameGenerator;

    @Transactional
    public Member login(String socialId, String nickname, String imageUrl) {
        Member member = memberRepository.findBySocialId(socialId)
            .orElseGet(() -> createMember(socialId, nickname, imageUrl));
        Member loginMember = member.login(clockProvider.millis());
        return memberRepository.save(loginMember);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member existMember = getMemberById(memberId);

        Member deletedMember = existMember.delete(clockProvider.millis());
        memberRepository.save(deletedMember);
    }

    @Transactional
    void updateMember(Long memberId, String nickname, String imageUrl) {
        Member existMember = getMemberById(memberId);

        if (!existMember.isSameNickname(nickname)) {
            checkUniqueNickname(nickname);
        }
        Member updatedMember = existMember.update(nickname, imageUrl, clockProvider.millis());
        memberRepository.save(updatedMember);
    }

    private void checkUniqueNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw ErrorCode.DUPLICATED_MEMBER_NICKNAME.toException();
        }
    }

    Member createMember(String socialId, String nickname, String imageUrl) {
        String uniqueNickname = nicknameGenerator.generateUniqueNickname(nickname);
        Member newMember = Member.of(socialId, uniqueNickname, imageUrl, clockProvider.millis());
        return memberRepository.save(newMember);
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_MEMBER::toException);
    }

}
