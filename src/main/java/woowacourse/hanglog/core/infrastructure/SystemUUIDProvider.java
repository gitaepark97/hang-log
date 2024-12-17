package woowacourse.hanglog.core.infrastructure;

import org.springframework.stereotype.Component;
import woowacourse.hanglog.core.application.port.UUIDProvider;

import java.util.UUID;

@Component
class SystemUUIDProvider implements UUIDProvider {

    @Override
    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
