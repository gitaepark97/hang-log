package woowacourse.hanglog.web;

import woowacourse.hanglog.core.member.domain.Member;

abstract class MemberResponse {

    record MyPage(
        Long id,
        String nickname,
        String imageUrl
    ) {

        public static MyPage from(Member member) {
            return new MyPage(member.id(), member.nickname(), member.imageUrl());
        }

    }

}

