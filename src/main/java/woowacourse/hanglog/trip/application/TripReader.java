package woowacourse.hanglog.trip.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.trip.application.port.TripRepository;
import woowacourse.hanglog.trip.domain.Trip;

@RequiredArgsConstructor
@Component
class TripReader {

    private final TripRepository tripRepository;

    Page<Trip> getMemberTrips(Long memberId, Pageable pageable) {
        return tripRepository.findAllByMemberId(memberId, pageable);
    }

}
