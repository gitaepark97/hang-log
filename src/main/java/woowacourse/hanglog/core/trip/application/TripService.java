package woowacourse.hanglog.core.trip.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.trip.domain.City;
import woowacourse.hanglog.core.trip.domain.TripWithCities;
import woowacourse.hanglog.core.member.application.MemberReader;

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
