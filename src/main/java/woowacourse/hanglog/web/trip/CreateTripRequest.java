package woowacourse.hanglog.web.trip;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

record CreateTripRequest(
    @NotNull(message = "여행 시작 날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate,

    @NotNull(message = "여행 종료 날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate,

    @NotEmpty(message = "여행한 도시는 최소 한 개 이상 입력해 주세요.")
    List<Long> cityIds
) {

}
