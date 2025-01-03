package woowacourse.hanglog.member.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.port.MemberRepository;
import woowacourse.hanglog.member.domain.Member;
import woowacourse.hanglog.support.exception.ErrorCode;
import woowacourse.hanglog.support.provider.ClockProvider;

@RequiredArgsConstructor
@Component
class MemberProcessor {

    private final ClockProvider clockProvider;
    private final MemberRepository memberRepository;
    private final NicknameGenerator nicknameGenerator;

    @Transactional
    Member login(String socialId, String nickname, String imageUrl) {
        Member member = memberRepository.findBySocialId(socialId)
            .orElseGet(() -> createMember(socialId, nickname, imageUrl));
        Member loginMember = member.login(clockProvider.millis());
        return memberRepository.save(loginMember);
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

    @Transactional
    void deleteMemberById(Long memberId) {
        Member existMember = getMemberById(memberId);

        Member deletedMember = existMember.delete(clockProvider.millis());
        memberRepository.save(deletedMember);
    }

    private void checkUniqueNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw ErrorCode.DUPLICATED_MEMBER_NICKNAME.toException();
        }
    }

    private Member createMember(String socialId, String nickname, String imageUrl) {
        String uniqueNickname = nicknameGenerator.generateUniqueNickname(nickname);
        Member newMember = Member.of(socialId, uniqueNickname, imageUrl, clockProvider.millis());
        return memberRepository.save(newMember);
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(ErrorCode.NOT_FOUND_MEMBER::toException);
    }

}
