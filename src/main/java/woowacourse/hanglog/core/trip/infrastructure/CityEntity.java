package woowacourse.hanglog.core.trip.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import woowacourse.hanglog.core.trip.domain.City;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "city")
class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private BigDecimal latitude;

    private BigDecimal longitude;

    static CityEntity from(City city) {
        return new CityEntity(
            city.id(),
            city.name(),
            city.country(),
            city.latitude(),
            city.longitude()
        );
    }

    City toCity() {
        return City.builder()
            .id(id)
            .name(name)
            .country(country)
            .latitude(latitude)
            .longitude(longitude)
            .build();
    }

}
