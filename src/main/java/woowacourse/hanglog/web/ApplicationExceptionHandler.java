package woowacourse.hanglog.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import woowacourse.hanglog.support.exception.ApplicationException;
import woowacourse.hanglog.support.exception.ErrorCode;

@Slf4j
@RestControllerAdvice
class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    ApplicationException handleApplicationException(ApplicationException e) {
        return e;
    }

    @ExceptionHandler(Exception.class)
    ApplicationException handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorCode.INTERNAL_SERVER_ERROR.toException();
    }

}
