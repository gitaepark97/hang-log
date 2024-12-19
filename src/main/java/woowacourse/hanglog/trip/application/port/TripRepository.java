package woowacourse.hanglog.trip.application.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.Trip;

import java.util.List;

public interface TripRepository {

    Page<Trip> findAllByMemberId(Long memberId, Pageable pageable);

    Trip save(Trip trip);

    void saveTripCities(Trip trip, List<City> cities);

}
