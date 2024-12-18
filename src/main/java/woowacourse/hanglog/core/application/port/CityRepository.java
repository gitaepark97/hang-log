package woowacourse.hanglog.core.application.port;

import woowacourse.hanglog.core.domain.City;
import woowacourse.hanglog.core.domain.Trip;

import java.util.List;

public interface CityRepository {

    List<City> findAllByIdIn(List<Long> ids);

    List<City> findAllByTrip(Trip trip);

}
