package woowacourse.hanglog.web.security.token;

public record AuthToken(
    String access,
    String refresh
) {

}
