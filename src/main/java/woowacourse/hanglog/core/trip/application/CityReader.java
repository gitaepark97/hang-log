package woowacourse.hanglog.core.trip.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.trip.application.port.CityRepository;
import woowacourse.hanglog.core.trip.domain.City;
import woowacourse.hanglog.core.trip.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Component
class CityReader {

    private final CityRepository cityRepository;

    List<City> getCitiesInIds(List<Long> cityIds) {
        return cityRepository.findAllByIdIn(cityIds);
    }

    List<City> getCitiesByTrip(Trip trip) {
        return cityRepository.findAllByTrip(trip);
    }

}
