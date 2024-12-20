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
        builder.andAnyOf(cityEntity.name.startsWithIgnoreCase(name), cityEntity.country.startsWithIgnoreCase(name));

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
        QCityEntity cityEntity = QCityEntity.cityEntity;
        QTripCityEntity tripCityEntity = QTripCityEntity.tripCityEntity;
        return query
            .select(cityEntity)
            .from(tripCityEntity)
            .join(cityEntity)
            .on(tripCityEntity.cityId.eq(cityEntity.id))
            .where(tripCityEntity.tripId.eq(trip.id()))
            .fetch()
            .stream()
            .map(CityEntity::toCity)
            .toList();
    }

}
