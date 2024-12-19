package woowacourse.hanglog.support.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RetryAspect.class})
class AspectConfig {

}
