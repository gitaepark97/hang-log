package woowacourse.hanglog.member.application.port;

import woowacourse.hanglog.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    boolean existsById(Long id);

    boolean existsByNickname(String nickname);

    Optional<Member> findById(Long id);

    Optional<Member> findBySocialId(String socialId);

    Member save(Member member);

}
