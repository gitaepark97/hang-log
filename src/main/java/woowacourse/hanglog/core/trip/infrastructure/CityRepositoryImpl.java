package woowacourse.hanglog.core.trip.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.core.trip.application.port.CityRepository;
import woowacourse.hanglog.core.trip.domain.City;
import woowacourse.hanglog.core.trip.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Repository
class CityRepositoryImpl implements CityRepository {

    private final CityEntityRepository cityEntityRepository;
    private final TripCityEntityRepository tripCityEntityRepository;

    @Override
    public List<City> findAllByIdIn(List<Long> ids) {
        return cityEntityRepository.findAllByIdIn(ids).stream().map(CityEntity::toCity).toList();
    }

    @Override
    public List<City> findAllByTrip(Trip trip) {
        return tripCityEntityRepository.findAllByTripId(trip.id())
            .stream()
            .map(tripCity -> tripCity.getCity().toCity())
            .toList();
    }

}
