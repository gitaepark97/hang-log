package woowacourse.hanglog.web;

import org.springframework.http.HttpStatus;
import woowacourse.hanglog.support.exception.ApplicationException;

public record ApiResponse<T>(
    HttpStatus status,
    String message,
    T data
) {

    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return switch (status) {
            case HttpStatus.OK, HttpStatus.CREATED -> new ApiResponse<>(status, "성공", data);
            default -> new ApiResponse<>(status, "실패", data);
        };
    }

    public static ApiResponse<?> fail(ApplicationException e) {
        return new ApiResponse<>(e.getStatus(), e.getMessage(), null);
    }

}
