package woowacourse.hanglog.core.web.response;

import woowacourse.hanglog.core.domain.Member;

public record MyPageResponse(
    String nickname,
    String imageUrl
) {

    public static MyPageResponse from(Member member) {
        return new MyPageResponse(member.nickname(), member.imageUrl());
    }

}
