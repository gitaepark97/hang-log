package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.CityRepository;
import woowacourse.hanglog.core.domain.City;

import java.util.List;

@RequiredArgsConstructor
@Component
class CityReader {

    private final CityRepository cityRepository;

    List<City> getCitiesInIds(List<Long> cityIds) {
        return cityRepository.findAllByIdIn(cityIds);
    }

}
