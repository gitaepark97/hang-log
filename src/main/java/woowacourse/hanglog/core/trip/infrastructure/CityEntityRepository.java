package woowacourse.hanglog.core.trip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CityEntityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAllByIdIn(List<Long> ids);

}
