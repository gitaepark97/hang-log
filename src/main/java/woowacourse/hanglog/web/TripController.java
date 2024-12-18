package woowacourse.hanglog.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    Page<TripResponse.SimpleTrip> getMyTrips(@AuthenticationPrincipal Long memberId, Pageable pageable) {
        return tripService.getMemberTripWithCities(memberId, pageable).map(TripResponse.SimpleTrip::from);
    }

}
