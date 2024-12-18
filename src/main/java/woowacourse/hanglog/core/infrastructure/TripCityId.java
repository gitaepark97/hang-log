package woowacourse.hanglog.core.infrastructure;

import java.io.Serializable;

record TripCityId(
    Long tripId,
    Long cityId
) implements Serializable {

}
