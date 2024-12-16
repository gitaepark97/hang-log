package woowacourse.hanglog.core.infrastructure;

import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.ClockProvider;

import java.time.Clock;

@Component
class SystemClockProvider implements ClockProvider {

    @Override
    public Long millis() {
        return Clock.systemUTC().millis();
    }

}
