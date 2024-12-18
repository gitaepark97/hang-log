package woowacourse.hanglog.core.member.application.port;

import woowacourse.hanglog.core.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    boolean existsById(Long id);

    boolean existsByNickname(String nickname);

    Optional<Member> findById(Long id);

    Optional<Member> findBySocialId(String socialId);

    Member save(Member member);

}
