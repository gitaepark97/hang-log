package woowacourse.hanglog.core.trip.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record City(
    Long id,
    String name,
    String country,
    BigDecimal latitude,
    BigDecimal longitude
) {

}
