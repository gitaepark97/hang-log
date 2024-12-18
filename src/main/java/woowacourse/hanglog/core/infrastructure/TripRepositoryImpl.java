package woowacourse.hanglog.core.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.core.application.port.TripRepository;
import woowacourse.hanglog.core.domain.City;
import woowacourse.hanglog.core.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Repository
class TripRepositoryImpl implements TripRepository {

    private final TripEntityRepository tripEntityRepository;
    private final TripCityEntityRepository tripCityEntityRepository;

    @Override
    public Trip save(Trip trip) {
        return tripEntityRepository.save(TripEntity.from(trip)).toTrip();
    }

    @Override
    public void saveCities(Long id, List<City> cities) {
        List<TripCityEntity> tripCities = cities.stream().map(city -> TripCityEntity.of(id, city.id())).toList();
        tripCityEntityRepository.saveAll(tripCities);
    }

}
