package woowacourse.hanglog.core.application.port;

import woowacourse.hanglog.core.domain.City;
import woowacourse.hanglog.core.domain.Trip;

import java.util.List;

public interface TripRepository {

    Trip save(Trip trip);

    void saveCities(Long id, List<City> cities);

}
