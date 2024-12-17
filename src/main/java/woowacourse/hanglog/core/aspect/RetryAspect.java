package woowacourse.hanglog.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
class RetryAspect {

    @Around("@annotation(retry)")
    Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxRetry = retry.value();
        while (maxRetry-- > 0) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                if (maxRetry == 0) {
                    throw e;
                }
            }
        }
        return null;
    }

}
