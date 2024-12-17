package woowacourse.hanglog.core.domain;

import lombok.Builder;

@Builder
public record Session(
    String id,
    Long memberId,
    Long createTime
) {

    public static Session of(String id, Long memberId, Long currentTime) {
        return Session.builder()
            .id(id)
            .memberId(memberId)
            .createTime(currentTime)
            .build();
    }

}
