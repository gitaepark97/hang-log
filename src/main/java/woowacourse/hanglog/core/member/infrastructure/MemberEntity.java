package woowacourse.hanglog.core.member.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;
import woowacourse.hanglog.core.member.domain.Member;
import woowacourse.hanglog.core.member.domain.MemberStatus;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "member")
@DynamicInsert
@DynamicUpdate
@SQLRestriction("status = 'ACTIVE'")
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String socialId;

    @Column(unique = true, length = 20)
    private String nickname;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    private Long lastLoginTime;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long updateTime;

    private Long deleteTime;

    static MemberEntity from(Member member) {
        return new MemberEntity(
            member.id(),
            member.socialId(),
            member.nickname(),
            member.imageUrl(),
            member.status(),
            member.lastLoginTime(),
            member.createTime(),
            member.updateTime(),
            member.deleteTime()
        );
    }

    Member toMember() {
        return Member.builder()
            .id(id)
            .socialId(socialId)
            .nickname(nickname)
            .imageUrl(imageUrl)
            .status(status)
            .lastLoginTime(lastLoginTime)
            .createTime(createTime)
            .updateTime(updateTime)
            .deleteTime(deleteTime)
            .build();
    }

}