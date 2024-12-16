package woowacourse.hanglog.core.domain;

public record AuthToken(
    String access,
    String refresh
) {

}
