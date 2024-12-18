package woowacourse.hanglog.core.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface TripEntityRepository extends JpaRepository<TripEntity, Long> {

    Page<TripEntity> findAllByMemberId(Long memberId, Pageable pageable);

}
