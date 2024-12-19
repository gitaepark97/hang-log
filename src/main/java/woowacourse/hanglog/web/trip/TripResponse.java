package woowacourse.hanglog.web.trip;

import woowacourse.hanglog.trip.domain.TripWithCities;

import java.time.LocalDate;
import java.util.List;

record TripResponse(
    Long id,
    List<CityResponse> cities,
    String title,
    LocalDate startDate,
    LocalDate endDate,
    String description,
    String imageName
) {

    static TripResponse from(TripWithCities tripWithCities) {
        return new TripResponse(
            tripWithCities.trip().id(),
            tripWithCities.cities().stream().map(CityResponse::from).toList(),
            tripWithCities.trip().title(),
            tripWithCities.trip().startDate(),
            tripWithCities.trip().endDate(),
            tripWithCities.trip().description(),
            tripWithCities.trip().imageName()
        );
    }

}
