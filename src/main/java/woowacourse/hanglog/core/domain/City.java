package woowacourse.hanglog.core.domain;

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
