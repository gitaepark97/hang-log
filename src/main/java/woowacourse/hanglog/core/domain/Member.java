package woowacourse.hanglog.core.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Member(
    Long id,
    String socialId,
    String nickname,
    String imageUrl,
    MemberStatus status,
    Long lastLoginTime,
    Long createTime,
    Long updateTime,
    Long deleteTime
) {

    public static Member of(String socialId, String nickname, String imageUrl, Long currentTime) {
        return Member.builder()
            .socialId(socialId)
            .nickname(nickname)
            .imageUrl(imageUrl)
            .status(MemberStatus.ACTIVE)
            .createTime(currentTime)
            .updateTime(currentTime)
            .build();
    }

    public Member update(String nickname, String imageUrl, Long currentTime) {
        return toBuilder().nickname(nickname).imageUrl(imageUrl).updateTime(currentTime).build();
    }

    public boolean isSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    public Member login(Long currentTime) {
        return toBuilder().lastLoginTime(currentTime).build();
    }

    public Member delete(Long currentTime) {
        return toBuilder().deleteTime(currentTime).build();
    }

}
