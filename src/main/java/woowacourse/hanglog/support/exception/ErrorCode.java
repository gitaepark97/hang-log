package woowacourse.hanglog.support.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 필요합니다."),

    // Member
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 멤버가 존재하지 않습니다."),
    DUPLICATED_MEMBER_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    ;

    private final HttpStatus status;
    private final String message;

    public ApplicationException toException() {
        return new ApplicationException(this.status, this.message);
    }
}
