package woowacourse.hanglog.core.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RetryAspect.class})
class AspectConfig {

}
