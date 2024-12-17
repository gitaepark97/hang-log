package woowacourse.hanglog.core.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface SessionEntityRepository extends JpaRepository<SessionEntity, String> {

    void deleteByMemberId(Long memberId);

}
