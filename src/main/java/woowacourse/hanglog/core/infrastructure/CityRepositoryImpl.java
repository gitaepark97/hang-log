package woowacourse.hanglog.core.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowacourse.hanglog.core.application.port.CityRepository;
import woowacourse.hanglog.core.domain.City;

import java.util.List;

@RequiredArgsConstructor
@Repository
class CityRepositoryImpl implements CityRepository {

    private final CityEntityRepository cityEntityRepository;

    @Override
    public List<City> findAllByIdIn(List<Long> ids) {
        return cityEntityRepository.findAllByIdIn(ids).stream().map(CityEntity::toCity).toList();
    }

}
