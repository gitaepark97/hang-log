package woowacourse.hanglog.web;

import woowacourse.hanglog.core.domain.City;

abstract class CityResponse {

    record SimpleCity(
        Long id,
        String name
    ) {
        
        static SimpleCity from(City city) {
            return new SimpleCity(city.id(), String.format("%s(%s)", city.name(), city.country()));
        }

    }

}
