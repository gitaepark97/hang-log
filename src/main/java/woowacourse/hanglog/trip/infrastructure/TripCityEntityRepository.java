package woowacourse.hanglog.trip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TripCityEntityRepository extends JpaRepository<TripCityEntity, Long> {

    List<TripCityEntity> findAllByTripId(Long tripId);

}
