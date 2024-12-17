package woowacourse.hanglog.web.security;

record AuthToken(
    String access,
    String refresh
) {

}
