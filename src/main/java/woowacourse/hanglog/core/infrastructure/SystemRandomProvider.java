package woowacourse.hanglog.core.infrastructure;

import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.RandomProvider;

import java.util.concurrent.ThreadLocalRandom;

@Component
class SystemRandomProvider implements RandomProvider {

    @Override
    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
