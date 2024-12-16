package woowacourse.hanglog.core.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByNickname(String nickname);

    Optional<MemberEntity> findBySocialId(String socialId);

}
