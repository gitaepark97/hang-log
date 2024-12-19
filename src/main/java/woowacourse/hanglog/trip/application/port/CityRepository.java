package woowacourse.hanglog.trip.application.port;

import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.Trip;

import java.util.List;

public interface CityRepository {

    List<City> searchAllByName(String name);

    List<City> findAllByIdIn(List<Long> ids);

    List<City> findAllByTrip(Trip trip);

}
