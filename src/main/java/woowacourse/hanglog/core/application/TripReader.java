package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.TripRepository;
import woowacourse.hanglog.core.domain.Trip;

@RequiredArgsConstructor
@Component
class TripReader {

    private final TripRepository tripRepository;

    Page<Trip> getMemberTrips(Long memberId, Pageable pageable) {
        return tripRepository.findAllByMemberId(memberId, pageable);
    }

}
