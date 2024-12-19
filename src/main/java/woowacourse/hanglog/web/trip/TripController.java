package woowacourse.hanglog.web.trip;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trips")
class TripController {

    private final TripFacade tripFacade;

    @GetMapping("/cities")
    List<CityResponse> searchCities(@RequestParam String name) {
        return tripFacade.searchCities(name);
    }

    @PostMapping
    void createTrip(@AuthenticationPrincipal Long memberId, @RequestBody @Valid CreateTripRequest request) {
        tripFacade.createTrip(memberId, request);
    }

    @GetMapping
    Page<TripResponse> getMyTrips(@AuthenticationPrincipal Long memberId, Pageable pageable) {
        return tripFacade.getMemberTripWithCities(memberId, pageable);
    }

}
