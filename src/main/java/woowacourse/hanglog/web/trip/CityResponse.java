package woowacourse.hanglog.web.trip;

import woowacourse.hanglog.trip.domain.City;

record CityResponse(
    Long id,
    String name
) {

    static CityResponse from(City city) {
        return new CityResponse(city.id(), String.format("%s(%s)", city.name(), city.country()));
    }

}
