package woowacourse.hanglog.web;

import woowacourse.hanglog.core.domain.TripWithCities;

import java.time.LocalDate;
import java.util.List;

abstract class TripResponse {

    record SimpleTrip(
        Long id,
        List<CityResponse.SimpleCity> cities,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        String description,
        String imageName
    ) {

        static SimpleTrip from(TripWithCities tripWithCities) {
            return new SimpleTrip(
                tripWithCities.trip().id(),
                tripWithCities.cities().stream().map(CityResponse.SimpleCity::from).toList(),
                tripWithCities.trip().title(),
                tripWithCities.trip().startDate(),
                tripWithCities.trip().endDate(),
                tripWithCities.trip().description(),
                tripWithCities.trip().imageName()
            );
        }

    }

}
