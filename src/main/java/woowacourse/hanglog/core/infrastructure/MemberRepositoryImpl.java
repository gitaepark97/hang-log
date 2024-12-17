package woowacourse.hanglog.core.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.core.application.port.MemberRepository;
import woowacourse.hanglog.core.domain.Member;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class MemberRepositoryImpl implements MemberRepository {

    private final MemberEntityRepository memberEntityRepository;

    @Override
    public boolean existsById(Long id) {
        return memberEntityRepository.existsById(id);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return memberEntityRepository.existsByNickname(nickname);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberEntityRepository.findById(id).map(MemberEntity::toMember);
    }

    @Override
    public Optional<Member> findBySocialId(String socialId) {
        return memberEntityRepository.findBySocialId(socialId).map(MemberEntity::toMember);
    }

    @Override
    public Member save(Member member) {
        return memberEntityRepository.save(MemberEntity.from(member)).toMember();
    }

}
