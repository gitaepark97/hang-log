package woowacourse.hanglog.trip.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.TripWithCities;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final CityReader cityReader;
    private final TripReader tripReader;
    private final TripProcessor tripProcessor;

    public List<City> searchCitiesByName(String name) {
        return cityReader.searchCitiesByName(name);
    }

    public Page<TripWithCities> getMemberTripWithCities(Long memberId, Pageable pageable) {
        return tripReader.getMemberTrips(memberId, pageable).map(trip -> {
            List<City> cities = cityReader.getCitiesByTrip(trip);
            return TripWithCities.of(trip, cities);
        });
    }

    public void createTrip(Long memberId, LocalDate startDate, LocalDate endDate, List<Long> cityIds) {
        List<City> cities = cityReader.getCitiesInIds(cityIds);
        tripProcessor.createTrip(memberId, startDate, endDate, cities);
    }

}
