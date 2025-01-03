package woowacourse.hanglog.trip.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "trip_city")
@IdClass(TripCityId.class)
class TripCityEntity {

    @Id
    private Long tripId;

    @Getter
    @Id
    private Long cityId;

    static TripCityEntity of(Long tripId, Long cityId) {
        return new TripCityEntity(tripId, cityId);
    }

}
