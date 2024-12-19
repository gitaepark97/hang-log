package woowacourse.hanglog.trip.infrastructure;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.trip.application.port.CityRepository;
import woowacourse.hanglog.trip.domain.City;
import woowacourse.hanglog.trip.domain.Trip;

import java.util.List;

@RequiredArgsConstructor
@Repository
class CityRepositoryImpl implements CityRepository {

    private final JPAQueryFactory query;
    private final CityEntityRepository cityEntityRepository;
    private final TripCityEntityRepository tripCityEntityRepository;

    @Override
    public List<City> searchAllByName(String name) {
        QCityEntity cityEntity = QCityEntity.cityEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.or(cityEntity.name.startsWithIgnoreCase(name));
        builder.or(cityEntity.country.startsWithIgnoreCase(name));

        return query.select(cityEntity)
            .from(cityEntity)
            .where(builder)
            .fetch()
            .stream()
            .map(CityEntity::toCity)
            .toList();
    }

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
