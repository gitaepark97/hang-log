package woowacourse.hanglog.core.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "trip_city")
class TripCityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private TripEntity trip;

    @Getter
    @ManyToOne(optional = false)
    private CityEntity city;

    static TripCityEntity of(TripEntity trip, CityEntity city) {
        return new TripCityEntity(null, trip, city);
    }

}
