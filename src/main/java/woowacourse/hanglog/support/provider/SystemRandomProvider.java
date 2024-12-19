package woowacourse.hanglog.support.provider;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
class SystemRandomProvider implements RandomProvider {

    @Override
    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
