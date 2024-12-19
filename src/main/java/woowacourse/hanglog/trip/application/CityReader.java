package woowacourse.hanglog.trip.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.trip.application.port.CityRepository;
import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Component
class CityReader {

    private final CityRepository cityRepository;

    List<City> searchCitiesByName(String name) {
        return cityRepository.searchAllByName(name);
    }

    List<City> getCitiesInIds(List<Long> cityIds) {
        return cityRepository.findAllByIdIn(cityIds);
    }

    List<City> getCitiesByTrip(Trip trip) {
        return cityRepository.findAllByTrip(trip);
    }

}
