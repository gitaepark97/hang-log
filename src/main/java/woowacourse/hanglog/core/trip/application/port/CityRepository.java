package woowacourse.hanglog.core.trip.application.port;

import woowacourse.hanglog.core.trip.domain.City;
import woowacourse.hanglog.core.trip.domain.Trip;

import java.util.List;

public interface CityRepository {

    List<City> findAllByIdIn(List<Long> ids);

    List<City> findAllByTrip(Trip trip);

}
