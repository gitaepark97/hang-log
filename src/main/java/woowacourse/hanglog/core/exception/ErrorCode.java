package woowacourse.hanglog.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 멤버가 존재하지 않습니다."),
    DUPLICATED_MEMBER_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
