package woowacourse.hanglog.trip.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.trip.application.port.TripRepository;
import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Repository
class TripRepositoryImpl implements TripRepository {

    private final TripEntityRepository tripEntityRepository;
    private final TripCityEntityRepository tripCityEntityRepository;

    @Override
    public Page<Trip> findAllByMemberId(Long memberId, Pageable pageable) {
        return tripEntityRepository.findAllByMemberId(memberId, pageable).map(TripEntity::toTrip);
    }

    @Override
    public Trip save(Trip trip) {
        return tripEntityRepository.save(TripEntity.from(trip)).toTrip();
    }

    @Override
    public void saveTripCities(Trip trip, List<City> cities) {
        TripEntity tripEntity = TripEntity.from(trip);
        List<TripCityEntity> tripCities = cities.stream()
            .map(city -> TripCityEntity.of(tripEntity, CityEntity.from(city)))
            .toList();
        tripCityEntityRepository.saveAll(tripCities);
    }

}
