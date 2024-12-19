package woowacourse.hanglog.web.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.member.application.MemberService;
import woowacourse.hanglog.trip.application.TripService;

import java.util.List;

@RequiredArgsConstructor
@Component
class TripFacade {

    private final MemberService memberService;
    private final TripService tripService;

    List<CityResponse> searchCities(String name) {
        return tripService.searchCitiesByName(name).stream().map(CityResponse::from).toList();
    }

    Page<TripResponse> getMemberTripWithCities(Long memberId, Pageable pageable) {
        return tripService.getMemberTripWithCities(memberId, pageable).map(TripResponse::from);
    }

    void createTrip(Long memberId, CreateTripRequest request) {
        memberService.checkExistMember(memberId);
        tripService.createTrip(memberId, request.startDate(), request.endDate(), request.cityIds());
    }

}
