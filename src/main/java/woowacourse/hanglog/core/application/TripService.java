package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.domain.City;
import woowacourse.hanglog.core.domain.TripWithCities;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final MemberReader memberReader;
    private final CityReader cityReader;
    private final TripReader tripReader;
    private final TripProcessor tripProcessor;

    public Page<TripWithCities> getMemberTripWithCities(Long memberId, Pageable pageable) {
        return tripReader.getMemberTrips(memberId, pageable).map(trip -> {
            List<City> cities = cityReader.getCitiesByTrip(trip);
            return TripWithCities.of(trip, cities);
        });
    }

    public void createTrip(Long memberId, LocalDate startDate, LocalDate endDate, List<Long> cityIds) {
        memberReader.checkExistMember(memberId);
        List<City> cities = cityReader.getCitiesInIds(cityIds);
        tripProcessor.createTrip(memberId, startDate, endDate, cities);
    }

}
