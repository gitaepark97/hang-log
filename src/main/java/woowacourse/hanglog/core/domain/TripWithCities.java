package woowacourse.hanglog.core.domain;

import java.util.List;

public record TripWithCities(
    Trip trip,
    List<City> cities
) {

    public static TripWithCities of(Trip trip, List<City> cities) {
        return new TripWithCities(trip, cities);
    }

}
