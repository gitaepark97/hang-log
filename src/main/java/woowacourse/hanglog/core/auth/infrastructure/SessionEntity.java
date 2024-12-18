package woowacourse.hanglog.core.auth.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import woowacourse.hanglog.core.auth.domain.Session;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "session")
class SessionEntity {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Column(nullable = false)
    private Long createTime;

    static SessionEntity from(Session session) {
        return new SessionEntity(session.id(), session.memberId(), session.createTime());
    }

    Session toSession() {
        return Session.builder()
            .id(id)
            .memberId(memberId)
            .createTime(createTime)
            .build();
    }

}
