package woowacourse.hanglog.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface SessionEntityRepository extends JpaRepository<SessionEntity, String> {

    void deleteByMemberId(Long memberId);

}
