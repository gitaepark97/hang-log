package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.hanglog.core.application.port.ClockProvider;
import woowacourse.hanglog.core.application.port.TripRepository;
import woowacourse.hanglog.core.domain.City;
import woowacourse.hanglog.core.domain.Trip;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
class TripProcessor {

    private static final String TITLE_POSTFIX = " 여행";

    private final ClockProvider clockProvider;
    private final TripRepository tripRepository;

    @Transactional
    void createTrip(Long memberId, LocalDate startDate, LocalDate endDate, List<City> cities) {
        Trip trip = Trip.of(memberId, generateInitTitle(cities), startDate, endDate, clockProvider.millis());
        Trip savedTrip = tripRepository.save(trip);
        tripRepository.saveTripCities(savedTrip, cities);
    }

    private String generateInitTitle(List<City> cities) {
        return cities.getFirst().name() + TITLE_POSTFIX;
    }

}
