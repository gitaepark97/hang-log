package woowacourse.hanglog.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationException(ErrorCode code) {
        super(code.getMessage());
        this.status = code.getStatus();
    }

}
