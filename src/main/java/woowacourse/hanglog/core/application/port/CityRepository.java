package woowacourse.hanglog.core.application.port;

import woowacourse.hanglog.core.domain.City;

import java.util.List;

public interface CityRepository {

    List<City> findAllByIdIn(List<Long> ids);

}
