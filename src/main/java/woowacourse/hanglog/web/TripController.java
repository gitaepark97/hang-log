package woowacourse.hanglog.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.hanglog.core.application.TripService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trips")
class TripController {

    private final TripService tripService;

    @PostMapping
    void createTrip(@AuthenticationPrincipal Long memberId, @RequestBody @Valid TripRequest.CreateTrip request) {
        tripService.createTrip(memberId, request.startDate(), request.endDate(), request.cityIds());
    }

}
