package woowacourse.hanglog.web.member;

import woowacourse.hanglog.member.domain.Member;

record MyPageResponse(
    Long id,
    String nickname,
    String imageUrl
) {

    static MyPageResponse from(Member member) {
        return new MyPageResponse(member.id(), member.nickname(), member.imageUrl());
    }


}

