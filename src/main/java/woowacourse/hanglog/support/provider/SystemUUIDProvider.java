package woowacourse.hanglog.support.provider;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class SystemUUIDProvider implements UUIDProvider {

    @Override
    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
