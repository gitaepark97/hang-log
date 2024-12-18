package woowacourse.hanglog.core.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woowacourse.hanglog.core.domain.City;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final MemberReader memberReader;
    private final CityReader cityReader;
    private final TripProcessor tripProcessor;

    public void createTrip(Long memberId, LocalDate startDate, LocalDate endDate, List<Long> cityIds) {
        memberReader.checkExistMember(memberId);
        List<City> cities = cityReader.getCitiesInIds(cityIds);
        tripProcessor.createTrip(memberId, startDate, endDate, cities);
    }

}
